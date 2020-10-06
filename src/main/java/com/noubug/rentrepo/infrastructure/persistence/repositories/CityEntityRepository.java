package com.noubug.rentrepo.infrastructure.persistence.repositories;

import com.noubug.rentrepo.infrastructure.persistence.entities.CityEntity;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityEntityRepository extends DatastoreRepository<CityEntity, Long> {
    Optional<CityEntity> findByNameAndAndSubCountryAndAndCountry(String name, String subCountry, String country);

    Page<CityEntity> findByNameGreaterThanEqualAndNameLessThan(String queryStr, String queryString, Pageable pageable);
}
