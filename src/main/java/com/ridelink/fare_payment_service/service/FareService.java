package com.ridelink.fare_payment_service.service;

import com.ridelink.fare_payment_service.exception.ResourceNotFoundException;
import com.ridelink.fare_payment_service.dto.FareEstimateRequest;
import com.ridelink.fare_payment_service.model.Fare;
import com.ridelink.fare_payment_service.repository.FareRepository;
import org.springframework.stereotype.Service;
import com.ridelink.fare_payment_service.dto.FinalFareRequest;

@Service
public class FareService {

    private final FareRepository fareRepository;

    private static final double BASE_FARE = 200.0;
    private static final double RATE_PER_KM = 80.0;
    private static final double WAITING_RATE_PER_MINUTE = 20.0;

    public FareService(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }

    public Fare estimateFare(FareEstimateRequest request) {

        double estimatedFare =
                BASE_FARE + (request.getDistanceKm() * RATE_PER_KM);

        Fare fare = new Fare();

        fare.setRideId(request.getRideId());
        fare.setDistanceKm(request.getDistanceKm());

        fare.setBaseFare(BASE_FARE);
        fare.setRatePerKm(RATE_PER_KM);

        fare.setWaitingCharge(0);
        fare.setEstimatedFare(estimatedFare);
        fare.setFinalFare(0);

        return fareRepository.save(fare);
    }

    public Fare calculateFinalFare(FinalFareRequest request) {

        Fare fare = fareRepository.findByRideId(request.getRideId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fare not found for ride"));

        double waitingCharge =
                request.getWaitingMinutes() * WAITING_RATE_PER_MINUTE;

        double finalFare =
                fare.getEstimatedFare() + waitingCharge;

        fare.setWaitingCharge(waitingCharge);
        fare.setFinalFare(finalFare);

        return fareRepository.save(fare);
    }
    public Fare getFareByRideId(String rideId) {

        return fareRepository.findByRideId(rideId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fare not found for ride"));
    }
}