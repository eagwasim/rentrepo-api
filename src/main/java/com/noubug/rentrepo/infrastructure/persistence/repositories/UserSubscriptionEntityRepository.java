package com.noubug.rentrepo.infrastructure.persistence.repositories;

import com.noubug.rentrepo.infrastructure.persistence.entities.UserSubscriptionEntity;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface UserSubscriptionEntityRepository extends DatastoreRepository<UserSubscriptionEntity, Long> {
}
