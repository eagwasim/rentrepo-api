package com.noubug.rentrepo.infrastructure.persistence.entities;

import lombok.*;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PlacesToShare")
public class PlaceToShareEntity extends AbstractPersistableEntity<Long> {
    private String headline;
    private String description;
    private String currency;
    private String rate;
    private String paymentReOccurrence;
    private String addressCity;
    private String addressCountry;
    private String addressRegion;

    private String ownerHasPets;
    private String ownerSmokes;
    private String ownerWorkSchedule;

    private String genderPreference;
    private String employmentStatusPreference;
    private String petPreference;

    private Integer minAgePreference;
    private Integer maxAgePreference;

    private String[] availableServices;
    private String[] listingImages;

    private Double monthlyRate;
    private Double monthlyRateInBaseCurrency;

    private LocalDate dateAvailable;
    private LocalDate datePublished;
    private LocalDate expirationDate;

    private Boolean published;

    private UserEntity publisher;
}
