package com.noubug.rentrepo.usecases.impl;

import com.noubug.rentrepo.domain.CityDomain;
import com.noubug.rentrepo.domain.gateway.CityDomainGateway;
import com.noubug.rentrepo.infrastructure.web.controllers.model.CityRequest;
import com.noubug.rentrepo.usecases.DataPreloadingUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.ResourceUtils;

import javax.inject.Named;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

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
    public void createCity(CityRequest cityRequest) {
        this.cityDomainGateway.save(CityDomain.builder()
                .name(cityRequest.getName())
                .country(cityRequest.getCountry())
                .subCountry(cityRequest.getSubCountry())
                .geoNameId(cityRequest.getGeoNameId())
                .dateCreated(LocalDateTime.now(ZoneOffset.UTC))
                .lastUpdated(LocalDateTime.now(ZoneOffset.UTC))
                .build());
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
