package com.noubug.rentrepo.infrastructure.persistence.search;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@Builder
@TypeAlias("cities")
@Document(indexName = "rent_repo_cities", replicas = 0)
public class CityDocument {
    @Id
    private String id;
    @Field(type = FieldType.Search_As_You_Type)
    private String name;
    @GeoPointField
    private GeoPoint latLon;

    @Override
    public String toString() {
        return  name;
    }
}
