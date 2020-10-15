package com.noubug.rentrepo.infrastructure.web.controllers;

import com.noubug.rentrepo.infrastructure.web.controllers.model.CityRequestJSON;
import com.noubug.rentrepo.usecases.DataPreloadingUseCase;
import com.noubug.rentrepo.usecases.SearchCitiesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/public/cities")
public class CityController {
    private final DataPreloadingUseCase dataPreloadingUseCase;
    private final SearchCitiesUseCase searchCitiesUseCase;

    public CityController(DataPreloadingUseCase dataPreloadingUseCase, SearchCitiesUseCase searchCitiesUseCase) {
        this.dataPreloadingUseCase = dataPreloadingUseCase;
        this.searchCitiesUseCase = searchCitiesUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createCity(@RequestBody CityRequestJSON cityRequestJSON) {
        dataPreloadingUseCase.createCity(cityRequestJSON);
        return ResponseEntity.ok("done");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCities() {
        dataPreloadingUseCase.clear();
        return ResponseEntity.ok("done");
    }


    @PostMapping("/multiple")
    public ResponseEntity<?> createCities(@RequestBody List<CityRequestJSON> cityRequestJSON) {
        dataPreloadingUseCase.createCities(cityRequestJSON);
        return ResponseEntity.ok("done");
    }

    @GetMapping
    public ResponseEntity<?> loadCities() {
        dataPreloadingUseCase.preLoadCities();
        return ResponseEntity.ok("done");
    }

    @GetMapping("index")
    public ResponseEntity<?> indexCites() {
        dataPreloadingUseCase.indexCities();
        return ResponseEntity.ok("done");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCities(@RequestParam("q") String queryParam) {
        return ResponseEntity.ok(searchCitiesUseCase.search(queryParam));
    }
}
