package com.ridelink.fare_payment_service.controller;

import com.ridelink.fare_payment_service.dto.FareEstimateRequest;
import com.ridelink.fare_payment_service.model.Fare;
import com.ridelink.fare_payment_service.service.FareService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ridelink.fare_payment_service.dto.FinalFareRequest;

@RestController
@RequestMapping("/api/fares")
public class FareController {

    private final FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }

    @PostMapping("/estimate")
    public ResponseEntity<Fare> estimateFare(
            @Valid @RequestBody FareEstimateRequest request) {

        return ResponseEntity.ok(
                fareService.estimateFare(request)
        );
    }
    @PostMapping("/final")
    public ResponseEntity<Fare> calculateFinalFare(
            @Valid @RequestBody FinalFareRequest request) {

        return ResponseEntity.ok(
                fareService.calculateFinalFare(request)
        );
    }
    @GetMapping("/ride/{rideId}")
    public ResponseEntity<Fare> getFare(
            @PathVariable String rideId) {

        return ResponseEntity.ok(
                fareService.getFareByRideId(rideId)
        );
    }
}