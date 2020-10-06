package com.noubug.rentrepo.usecases;

import com.noubug.rentrepo.infrastructure.web.controllers.model.CityRequest;

public interface DataPreloadingUseCase {
    void preLoadCities();

    void indexCities();

    void clear();

    void createCity(CityRequest cityRequest);
}
