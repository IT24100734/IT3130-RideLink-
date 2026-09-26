package com.ridelink.fare_payment_service.controller;

import com.ridelink.fare_payment_service.dto.PaymentRequest;
import com.ridelink.fare_payment_service.model.Payment;
import com.ridelink.fare_payment_service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        return ResponseEntity.ok(
                paymentService.createPayment(request)
        );
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(
            @PathVariable String paymentId) {

        return ResponseEntity.ok(
                paymentService.getPaymentById(paymentId)
        );
    }

    @GetMapping("/ride/{rideId}")
    public ResponseEntity<Payment> getPaymentByRideId(
            @PathVariable String rideId) {

        return ResponseEntity.ok(
                paymentService.getPaymentByRideId(rideId)
        );
    }
}