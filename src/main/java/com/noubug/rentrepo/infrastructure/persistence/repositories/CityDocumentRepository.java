package com.noubug.rentrepo.infrastructure.persistence.repositories;

import com.noubug.rentrepo.infrastructure.persistence.search.CityDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CityDocumentRepository extends ElasticsearchRepository<CityDocument, String> {
}
