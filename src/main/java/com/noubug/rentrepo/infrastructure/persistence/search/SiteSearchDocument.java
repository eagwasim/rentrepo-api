package com.noubug.rentrepo.infrastructure.persistence.search;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@Builder
@TypeAlias("siteLinks")
@Document(indexName = "rent_repo_site_links", replicas = 0)
public class SiteSearchDocument {
    private String title;
    private String description;
    private String location;
    private String imageUrl;
    private String siteUrl;
    private String searchText;
    private GeoPoint latLong;
}
