package com.noubug.rentrepo.domain.gateway;

import com.noubug.rentrepo.domain.CityDomain;

public interface CityDomainGateway {
    void save(CityDomain cityDomain);

    void indexAll();

    void deleteAll();

    long count();

    String[] search(String queryParam);
}
