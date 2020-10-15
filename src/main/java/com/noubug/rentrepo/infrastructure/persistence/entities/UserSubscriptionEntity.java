package com.noubug.rentrepo.infrastructure.persistence.entities;

import lombok.*;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UsersSubscription")
public class UserSubscriptionEntity extends AbstractPersistableEntity<Long> {
    private SubscriptionEntity subscription;
    private UserEntity user;
    private LocalDate expirationDate;
}
