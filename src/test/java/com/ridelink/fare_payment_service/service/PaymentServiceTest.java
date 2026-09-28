package com.ridelink.fare_payment_service.service;

import com.ridelink.fare_payment_service.dto.PaymentRequest;
import com.ridelink.fare_payment_service.model.Payment;
import com.ridelink.fare_payment_service.model.PaymentStatus;
import com.ridelink.fare_payment_service.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PaymentServiceTest {

    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        paymentService = new PaymentService(paymentRepository);
    }

    @Test
    void createPayment_shouldReturnSuccess() {

        PaymentRequest request = new PaymentRequest();
        request.setRideId("RIDE001");
        request.setAmount(1100);
        request.setPaymentMethod("CARD");
        request.setSimulateFailure(false);

        Mockito.when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.createPayment(request);

        assertEquals(PaymentStatus.SUCCESS, result.getStatus());
        assertEquals(1100, result.getAmount());
        assertNotNull(result.getTransactionReference());
    }

    @Test
    void createPayment_shouldReturnFailedWhenSimulated() {

        PaymentRequest request = new PaymentRequest();
        request.setRideId("RIDE002");
        request.setAmount(900);
        request.setPaymentMethod("CARD");
        request.setSimulateFailure(true);

        Mockito.when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.createPayment(request);

        assertEquals(PaymentStatus.FAILED, result.getStatus());
    }
}