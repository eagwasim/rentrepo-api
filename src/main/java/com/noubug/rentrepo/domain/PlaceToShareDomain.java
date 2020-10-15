package com.noubug.rentrepo.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class PlaceToShareDomain {
    private Long id;

    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    private String headline;
    private String description;
    private String currency;
    private String rate;
    private String paymentReOccurrence;
    private String addressCity;
    private String addressCountry;
    private String addressRegion;
    private String status;

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

    private UserDomain publisher;
}
