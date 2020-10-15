package com.noubug.rentrepo.infrastructure.persistence.repositories;

import com.noubug.rentrepo.infrastructure.persistence.entities.UserPreferenceEntity;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface UserPreferenceEntityRepository extends DatastoreRepository<UserPreferenceEntity, Long> {
}
