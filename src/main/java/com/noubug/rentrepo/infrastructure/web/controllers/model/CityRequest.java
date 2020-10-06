package com.noubug.rentrepo.infrastructure.web.controllers.model;

import lombok.Data;

@Data
public class CityRequest {
    private String name;
    private String country;
    private String subCountry;
    private String geoNameId;
}
