package com.noubug.rentrepo.infrastructure.persistence.repositories;

import com.noubug.rentrepo.infrastructure.persistence.entities.CurrencyEntity;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import java.util.Optional;

public interface CurrencyEntityRepository extends DatastoreRepository<CurrencyEntity, Long> {
    Optional<CurrencyEntity> findFirstByCode(String code);
}
