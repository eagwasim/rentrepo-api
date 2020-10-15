package com.noubug.rentrepo.usecases.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlatShareRequestCreationRequest {
    private Long amount;
    private String availableServices;
    private String currency;
    private String dateAvailable;
    private String description;
    private String employmentStatusPreference;
    private String genderPreference;
    private String headline;
    private String location;
    private Integer maxAgePreference;
    private Integer minAgePreference;
    private String paymentFrequency;
    private String petPreference;
    private String petStatus;
    private String smokingStatus;
    private String workSchedule;

    private String[] images;
}
