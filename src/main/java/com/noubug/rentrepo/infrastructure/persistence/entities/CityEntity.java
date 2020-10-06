package com.noubug.rentrepo.infrastructure.persistence.entities;

import lombok.*;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cities")
public class CityEntity extends AbstractPersistableEntity<Long> {
    private String name;
    private String country;
    private String subCountry;
    private String geoNameId;
    private String latLon;


    @Override
    public String toString() {
        return String.format("%s, %s, %s", name, subCountry, country);
    }
}
