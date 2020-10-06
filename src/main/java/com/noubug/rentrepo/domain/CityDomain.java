package com.noubug.rentrepo.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CityDomain {
    private Long id;

    private String name;
    private String country;
    private String subCountry;
    private String geoNameId;
    private String latLon;

    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    @Override
    public String toString() {
        return String.format("%s, %s, %s", name, subCountry, country);
    }
}
