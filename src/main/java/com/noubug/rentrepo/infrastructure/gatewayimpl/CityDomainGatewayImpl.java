package com.noubug.rentrepo.infrastructure.gatewayimpl;

import com.noubug.rentrepo.domain.CityDomain;
import com.noubug.rentrepo.domain.gateway.CityDomainGateway;
import com.noubug.rentrepo.infrastructure.persistence.entities.CityEntity;
import com.noubug.rentrepo.infrastructure.persistence.repositories.CityDocumentRepository;
import com.noubug.rentrepo.infrastructure.persistence.repositories.CityEntityRepository;
import com.noubug.rentrepo.infrastructure.persistence.search.CityDocument;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class CityDomainGatewayImpl implements CityDomainGateway {
    private final CityEntityRepository cityEntityRepository;
    private final CityDocumentRepository cityDocumentRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public CityDomainGatewayImpl(CityEntityRepository cityEntityRepository, CityDocumentRepository cityDocumentRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.cityEntityRepository = cityEntityRepository;
        this.cityDocumentRepository = cityDocumentRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public void save(CityDomain cityDomain) {
        CityEntity c = from(cityDomain);

        cityEntityRepository.save(c);

        String[] latLon = c.getLatLon() == null ? null : c.getLatLon().split(",");

        cityDocumentRepository.save(CityDocument.builder()
                .name(c.toString())
                .latLon(latLon == null ? null : new GeoPoint(Double.valueOf(latLon[0]), Double.valueOf(latLon[1])))
                .id(c.getId().toString())
                .build());
    }

    @Override
    public void indexAll() {
        int page = 0;
        Page<CityEntity> cityEntities;
        PageRequest request;

        do {
            request = PageRequest.of(page++, 500, Sort.by("id"));
            cityEntities = cityEntityRepository.findAll(request);

            cityDocumentRepository.saveAll(
                    cityEntities.stream().map((c) -> {
                        String[] latLon = c.getLatLon() == null ? null : c.getLatLon().split(",");
                        return CityDocument.builder()
                                .name(c.toString())
                                .latLon(latLon == null ? null : new GeoPoint(Double.valueOf(latLon[0]), Double.valueOf(latLon[1])))
                                .id(c.getId().toString())
                                .build();
                    }).collect(Collectors.toList())
            );

        } while (cityEntities.hasNext());
    }

    @Override
    public void deleteAll() {
        try {
            cityEntityRepository.deleteAll();
        } catch (Exception ignore) {
        }
        try {
            cityDocumentRepository.deleteAll();
        } catch (Exception ignore) {
        }
    }

    @Override
    public long count() {
        return cityEntityRepository.count();
    }

    @Override
    public String[] search(String queryParam) {
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder().withPageable(PageRequest.of(0, 20));

        if (queryParam != null && !queryParam.trim().isEmpty()) {
            searchQuery.withFilter(QueryBuilders.matchPhrasePrefixQuery("name", queryParam.trim()));
        }

        SearchHits<CityDocument> documentSearchHits = elasticsearchRestTemplate.search(searchQuery.build(), CityDocument.class);

        List<String> result = documentSearchHits.get().map((r) -> r.getContent().toString()).collect(Collectors.toList());
        return result.toArray(new String[]{});
    }

    private CityEntity from(CityDomain cityDomain) {
        CityEntity cityEntity = CityEntity.builder()
                .country(cityDomain.getCountry())
                .name(cityDomain.getName())
                .latLon(cityDomain.getLatLon())
                .build();
        cityEntity.setDateCreated(cityDomain.getDateCreated());
        cityEntity.setDateModified(cityDomain.getLastUpdated());
        cityEntity.setId(cityDomain.getId());

        return cityEntity;
    }

    private CityDomain from(CityEntity cityEntity) {
        return CityDomain.builder()
                .country(cityEntity.getCountry())
                .name(cityEntity.getName())
                .dateCreated(cityEntity.getDateCreated())
                .lastUpdated(cityEntity.getDateModified())
                .latLon(cityEntity.getLatLon())
                .id(cityEntity.getId())
                .build();
    }
}
