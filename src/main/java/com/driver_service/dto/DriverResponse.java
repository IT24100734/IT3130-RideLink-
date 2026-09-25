package com.driver_service.dto;

import com.driver_service.model.AvailabilityStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverResponse {
    private String id;
    private String userId;
    private String fullName;
    private String phone;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleColor;
    private String licensePlate;
    private String vehicleType;
    private AvailabilityStatus availability;
    private String serviceArea;
    private Double currentLatitude;
    private Double currentLongitude;
}
