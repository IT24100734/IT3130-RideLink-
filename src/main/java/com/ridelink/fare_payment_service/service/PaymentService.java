package com.ridelink.fare_payment_service.service;

import com.ridelink.fare_payment_service.dto.PaymentRequest;
import com.ridelink.fare_payment_service.exception.ResourceNotFoundException;
import com.ridelink.fare_payment_service.model.Payment;
import com.ridelink.fare_payment_service.model.PaymentStatus;
import com.ridelink.fare_payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(PaymentRequest request) {

        Payment payment = new Payment();

        payment.setRideId(request.getRideId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());

        if (request.isSimulateFailure()) {
            payment.setStatus(PaymentStatus.FAILED);
        } else {
            payment.setStatus(PaymentStatus.SUCCESS);
        }

        payment.setTransactionReference(
                "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()
        );

        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(String paymentId) {

        return paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found"));
    }

    public Payment getPaymentByRideId(String rideId) {

        return paymentRepository.findByRideId(rideId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found for ride"));
    }
}