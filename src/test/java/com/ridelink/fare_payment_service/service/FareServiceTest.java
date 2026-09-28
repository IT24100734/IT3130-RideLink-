package com.ridelink.fare_payment_service.service;

import com.ridelink.fare_payment_service.dto.FareEstimateRequest;
import com.ridelink.fare_payment_service.dto.FinalFareRequest;
import com.ridelink.fare_payment_service.model.Fare;
import com.ridelink.fare_payment_service.repository.FareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class FareServiceTest {

    private FareRepository fareRepository;
    private FareService fareService;

    @BeforeEach
    void setUp() {
        fareRepository = Mockito.mock(FareRepository.class);
        fareService = new FareService(fareRepository);
    }

    @Test
    void estimateFare_shouldCalculateCorrectly() {

        FareEstimateRequest request = new FareEstimateRequest();
        request.setRideId("RIDE001");
        request.setDistanceKm(10);

        Mockito.when(fareRepository.save(any(Fare.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Fare result = fareService.estimateFare(request);

        assertEquals(1000.0, result.getEstimatedFare());
        assertEquals("RIDE001", result.getRideId());
    }

    @Test
    void calculateFinalFare_shouldAddWaitingCharge() {

        Fare existingFare = new Fare();
        existingFare.setRideId("RIDE001");
        existingFare.setEstimatedFare(1000);

        Mockito.when(fareRepository.findByRideId("RIDE001"))
                .thenReturn(Optional.of(existingFare));

        Mockito.when(fareRepository.save(any(Fare.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        FinalFareRequest request = new FinalFareRequest();
        request.setRideId("RIDE001");
        request.setWaitingMinutes(5);

        Fare result = fareService.calculateFinalFare(request);

        assertEquals(100.0, result.getWaitingCharge());
        assertEquals(1100.0, result.getFinalFare());
    }
}