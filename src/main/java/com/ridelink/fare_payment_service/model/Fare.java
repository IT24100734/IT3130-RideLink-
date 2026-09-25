package com.ridelink.fare_payment_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fares")
public class Fare {

    @Id
    private String id;

    private String rideId;
    private double distanceKm;

    private double baseFare;
    private double ratePerKm;

    private double waitingCharge;

    private double estimatedFare;
    private double finalFare;
}