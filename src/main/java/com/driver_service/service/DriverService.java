package com.driver_service.service;

import com.driver_service.dto.*;
import com.driver_service.exception.ApiException;
import com.driver_service.model.AvailabilityStatus;
import com.driver_service.model.Driver;
import com.driver_service.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverResponse register(RegisterDriverRequest request) {
        if (driverRepository.existsByUserId(request.getUserId())) {
            throw new ApiException("Driver profile already exists for this user", HttpStatus.CONFLICT);
        }
        Driver driver = Driver.builder()
                .userId(request.getUserId())
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .vehicleMake(request.getVehicleMake())
                .vehicleModel(request.getVehicleModel())
                .vehicleColor(request.getVehicleColor())
                .licensePlate(request.getLicensePlate())
                .vehicleType(request.getVehicleType() != null ? request.getVehicleType() : "SEDAN")
                .serviceArea(request.getServiceArea())
                .availability(AvailabilityStatus.OFFLINE)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        driver = driverRepository.save(driver);
        return toResponse(driver);
    }

    public DriverResponse getById(String id) {
        return toResponse(findOrThrow(id));
    }

    public DriverResponse getByUserId(String userId) {
        Driver d = driverRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException("Driver not found", HttpStatus.NOT_FOUND));
        return toResponse(d);
    }

    public DriverResponse updateAvailability(String id, UpdateAvailabilityRequest request) {
        Driver driver = findOrThrow(id);
        driver.setAvailability(request.getAvailability());
        if (request.getLatitude() != null) driver.setCurrentLatitude(request.getLatitude());
        if (request.getLongitude() != null) driver.setCurrentLongitude(request.getLongitude());
        driver.setUpdatedAt(Instant.now());
        return toResponse(driverRepository.save(driver));
    }

    public List<DriverResponse> getAvailableDrivers(String serviceArea) {
        List<Driver> drivers;
        if (serviceArea != null && !serviceArea.isBlank()) {
            drivers = driverRepository.findByAvailabilityAndServiceArea(AvailabilityStatus.AVAILABLE, serviceArea);
        } else {
            drivers = driverRepository.findByAvailability(AvailabilityStatus.AVAILABLE);
        }
        return drivers.stream().map(this::toResponse).collect(Collectors.toList());
    }

    private Driver findOrThrow(String id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ApiException("Driver not found", HttpStatus.NOT_FOUND));
    }

    private DriverResponse toResponse(Driver d) {
        return DriverResponse.builder()
                .id(d.getId())
                .userId(d.getUserId())
                .fullName(d.getFullName())
                .phone(d.getPhone())
                .vehicleMake(d.getVehicleMake())
                .vehicleModel(d.getVehicleModel())
                .vehicleColor(d.getVehicleColor())
                .licensePlate(d.getLicensePlate())
                .vehicleType(d.getVehicleType())
                .availability(d.getAvailability())
                .serviceArea(d.getServiceArea())
                .currentLatitude(d.getCurrentLatitude())
                .currentLongitude(d.getCurrentLongitude())
                .build();
    }
}
