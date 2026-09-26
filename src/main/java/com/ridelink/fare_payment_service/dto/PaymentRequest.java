package com.ridelink.fare_payment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotBlank(message = "Ride ID is required")
    private String rideId;

    @Positive(message = "Amount must be greater than 0")
    private double amount;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    private boolean simulateFailure;
}