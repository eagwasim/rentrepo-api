package com.noubug.rentrepo.infrastructure.persistence.repositories;

import com.noubug.rentrepo.infrastructure.persistence.entities.UserEntity;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import java.util.Optional;

public interface UserEntityRepository extends DatastoreRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByEmail(String email);
}
