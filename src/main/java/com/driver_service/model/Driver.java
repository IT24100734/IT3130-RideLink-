package com.driver_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    private String id;

    @Indexed(unique = true)
    private String userId;          // from Account Service

    private String fullName;
    private String phone;

    // Vehicle details
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleColor;
    private String licensePlate;
    private String vehicleType;     // e.g. SEDAN, SUV, HATCHBACK

    private AvailabilityStatus availability;
    private String serviceArea;     // e.g. "Colombo", "Kandy"
    private Double currentLatitude;
    private Double currentLongitude;

    private Instant createdAt;
    private Instant updatedAt;
}
