package com.noubug.rentrepo.usecases;

import com.noubug.rentrepo.infrastructure.web.controllers.model.CityRequestJSON;

import java.util.List;

public interface DataPreloadingUseCase {
    void preLoadCities();

    void indexCities();

    void clear();

    void createCity(CityRequestJSON cityRequestJSON);

    void createCities(List<CityRequestJSON> cityRequestJSON);
}
