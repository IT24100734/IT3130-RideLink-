package com.driver_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDriverRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String fullName;
    private String phone;
    @NotBlank
    private String vehicleMake;
    @NotBlank
    private String vehicleModel;
    private String vehicleColor;
    @NotBlank
    private String licensePlate;
    private String vehicleType = "SEDAN";
    private String serviceArea;
}
