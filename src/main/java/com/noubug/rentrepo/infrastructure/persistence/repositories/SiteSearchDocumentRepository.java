package com.noubug.rentrepo.infrastructure.persistence.repositories;

import com.noubug.rentrepo.infrastructure.persistence.search.SiteSearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SiteSearchDocumentRepository extends ElasticsearchRepository<SiteSearchDocument, String> {
}
