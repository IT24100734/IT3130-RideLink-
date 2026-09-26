package com.ridelink.fare_payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class FinalFareRequest {

    @NotBlank(message = "Ride ID is required")
    private String rideId;

    @PositiveOrZero(message = "Waiting minutes cannot be negative")
    private int waitingMinutes;
}