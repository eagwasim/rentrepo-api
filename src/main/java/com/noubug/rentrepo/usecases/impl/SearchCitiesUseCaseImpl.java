package com.noubug.rentrepo.usecases.impl;

import com.noubug.rentrepo.domain.gateway.CityDomainGateway;
import com.noubug.rentrepo.usecases.SearchCitiesUseCase;

import javax.inject.Named;

@Named
public class SearchCitiesUseCaseImpl implements SearchCitiesUseCase {
    private final CityDomainGateway cityDomainGateway;

    public SearchCitiesUseCaseImpl(CityDomainGateway cityDomainGateway) {
        this.cityDomainGateway = cityDomainGateway;
    }

    @Override
    public String[] search(String queryParam) {
        return cityDomainGateway.search(queryParam);
    }
}
