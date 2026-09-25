package com.driver_service.dto;

import com.driver_service.model.AvailabilityStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAvailabilityRequest {
    @NotNull
    private AvailabilityStatus availability;
    private Double latitude;
    private Double longitude;
}
