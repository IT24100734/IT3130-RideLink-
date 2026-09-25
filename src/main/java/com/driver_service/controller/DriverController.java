package com.driver_service.controller;

import com.driver_service.dto.*;
import com.driver_service.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
@Tag(name = "Drivers", description = "Driver operational profile and availability")
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    @Operation(summary = "Register driver operational profile + vehicle")
    public ResponseEntity<DriverResponse> register(@Valid @RequestBody RegisterDriverRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.register(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get driver by ID")
    public ResponseEntity<DriverResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(driverService.getById(id));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get driver by Account Service userId")
    public ResponseEntity<DriverResponse> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(driverService.getByUserId(userId));
    }

    @PutMapping("/{id}/availability")
    @Operation(summary = "Update availability status and optional location")
    public ResponseEntity<DriverResponse> updateAvailability(@PathVariable String id,
                                                             @Valid @RequestBody UpdateAvailabilityRequest request) {
        return ResponseEntity.ok(driverService.updateAvailability(id, request));
    }

    @GetMapping("/available")
    @Operation(summary = "Get eligible available drivers (optionally by service area)")
    public ResponseEntity<List<DriverResponse>> getAvailable(
            @RequestParam(required = false) String serviceArea) {
        return ResponseEntity.ok(driverService.getAvailableDrivers(serviceArea));
    }
}
