package com.noubug.rentrepo.infrastructure.web.controllers.model;

import lombok.Data;

@Data
public class CityRequestJSON {
    private String name;
    private String country;
    private String subCountry;
    private String geoNameId;
    private String latLon;
}
