package com.ridelink.fare_payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FareEstimateRequest {

    @NotBlank(message = "Ride ID is required")
    private String rideId;

    @Positive(message = "Distance must be greater than 0")
    private double distanceKm;
}