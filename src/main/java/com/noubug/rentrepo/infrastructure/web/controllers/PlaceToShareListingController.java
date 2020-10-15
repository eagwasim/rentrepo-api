package com.noubug.rentrepo.infrastructure.web.controllers;

import com.noubug.rentrepo.infrastructure.web.controllers.model.CreatePlaceToShareListingRequestJSON;
import com.noubug.rentrepo.infrastructure.web.controllers.model.ResponseJSON;
import com.noubug.rentrepo.usecases.UserCreatesFlatShareUseCase;
import com.noubug.rentrepo.usecases.model.FlatShareRequestCreationRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/listings/places/sharing")
public class PlaceToShareListingController {
    private final UserCreatesFlatShareUseCase userCreatesFlatShareUseCase;

    public PlaceToShareListingController(UserCreatesFlatShareUseCase userCreatesFlatShareUseCase) {
        this.userCreatesFlatShareUseCase = userCreatesFlatShareUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createPlaceToShare(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreatePlaceToShareListingRequestJSON requestJSON, @RequestHeader Map<String, String> headers) {
        Long id = userCreatesFlatShareUseCase.createPost(userDetails.getUsername(), FlatShareRequestCreationRequest.builder()
                .amount(requestJSON.getAmount())
                .availableServices(requestJSON.getAvailableServices())
                .currency(requestJSON.getCurrency())
                .dateAvailable(requestJSON.getDateAvailable())
                .description(requestJSON.getDescription())
                .employmentStatusPreference(requestJSON.getEmploymentStatusPreference())
                .genderPreference(requestJSON.getGenderPreference())
                .headline(requestJSON.getHeadline())
                .images(requestJSON.getImages())
                .location(requestJSON.getLocation())
                .maxAgePreference(requestJSON.getMaxAgePreference())
                .minAgePreference(requestJSON.getMinAgePreference())
                .paymentFrequency(requestJSON.getPaymentFrequency())
                .petPreference(requestJSON.getPetPreference())
                .petStatus(requestJSON.getPetStatus())
                .smokingStatus(requestJSON.getSmokingStatus())
                .workSchedule(requestJSON.getWorkSchedule())
                .build(), headers);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseJSON.builder().data(id).build());
    }
}
