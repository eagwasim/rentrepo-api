package com.noubug.rentrepo.infrastructure.persistence.entities;

import com.noubug.rentrepo.infrastructure.persistence.enumeration.SubscriptionTypeConstant;
import lombok.*;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Subscriptions")
public class SubscriptionEntity {
    private String name;

    private Integer subscriptionDurationInDays;
    private Integer maxListingsCount;
    private Integer maxListingPublishDuration;

    private SubscriptionTypeConstant type;
}
