package com.noubug.rentrepo.usecases.impl;

import com.noubug.rentrepo.domain.CityDomain;
import com.noubug.rentrepo.domain.gateway.CityDomainGateway;
import com.noubug.rentrepo.infrastructure.web.controllers.model.CityRequestJSON;
import com.noubug.rentrepo.usecases.DataPreloadingUseCase;
import lombok.extern.log4j.Log4j2;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Named
@Log4j2
public class DataPreloadingUseCaseImpl implements DataPreloadingUseCase {
    private final CityDomainGateway cityDomainGateway;

    public DataPreloadingUseCaseImpl(CityDomainGateway cityDomainGateway) {
        this.cityDomainGateway = cityDomainGateway;
    }

    @Override
    public void preLoadCities() {
        this.cityDomainGateway.deleteAll();
    }

    @Override
    public void indexCities() {
        this.cityDomainGateway.indexAll();
    }

    @Override
    public void clear() {
        this.cityDomainGateway.deleteAll();
    }

    @Override
    public void createCity(CityRequestJSON cityRequestJSON) {
        this.cityDomainGateway.save(CityDomain.builder()
                .name(cityRequestJSON.getName())
                .country(cityRequestJSON.getCountry())
                .subCountry(cityRequestJSON.getSubCountry())
                .geoNameId(cityRequestJSON.getGeoNameId())
                .dateCreated(LocalDateTime.now(ZoneOffset.UTC))
                .lastUpdated(LocalDateTime.now(ZoneOffset.UTC))
                .latLon(cityRequestJSON.getLatLon())
                .build());
    }

    @Override
    public void createCities(List<CityRequestJSON> cityRequestJSON) {
        cityRequestJSON.forEach(this::createCity);
    }

    private CityDomain from(String csvRow) {
        try {
            String[] items = csvRow.split(",");
            return CityDomain.builder()
                    .name(items[0].trim())
                    .country(items[1].trim())
                    .subCountry(items[2].trim())
                    .geoNameId(items[3].trim())
                    .dateCreated(LocalDateTime.now(ZoneOffset.UTC))
                    .lastUpdated(LocalDateTime.now(ZoneOffset.UTC))
                    .build();
        } catch (Exception e) {
            log.error("Error saving city " + csvRow, e);
        }
        return null;
    }
}
