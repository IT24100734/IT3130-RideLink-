package com.ridelink.fare_payment_service.service;

import com.ridelink.fare_payment_service.exception.InvalidOperationException;
import com.ridelink.fare_payment_service.model.Payment;
import com.ridelink.fare_payment_service.model.PaymentStatus;
import com.ridelink.fare_payment_service.model.Receipt;
import com.ridelink.fare_payment_service.repository.PaymentRepository;
import com.ridelink.fare_payment_service.repository.ReceiptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ReceiptServiceTest {

    private PaymentRepository paymentRepository;
    private ReceiptRepository receiptRepository;
    private ReceiptService receiptService;

    @BeforeEach
    void setUp() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        receiptRepository = Mockito.mock(ReceiptRepository.class);

        receiptService =
                new ReceiptService(receiptRepository, paymentRepository);
    }

    @Test
    void generateReceipt_shouldWorkForSuccessfulPayment() {

        Payment payment = new Payment();

        payment.setId("PAY001");
        payment.setRideId("RIDE001");
        payment.setAmount(1100);
        payment.setPaymentMethod("CARD");
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionReference("TXN-123");

        Mockito.when(paymentRepository.findById("PAY001"))
                .thenReturn(Optional.of(payment));

        Mockito.when(receiptRepository.save(any(Receipt.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Receipt receipt = receiptService.generateReceipt("PAY001");

        assertEquals("RIDE001", receipt.getRideId());
        assertEquals(1100, receipt.getAmount());
    }

    @Test
    void generateReceipt_shouldFailForFailedPayment() {

        Payment payment = new Payment();
        payment.setId("PAY002");
        payment.setStatus(PaymentStatus.FAILED);

        Mockito.when(paymentRepository.findById("PAY002"))
                .thenReturn(Optional.of(payment));

        assertThrows(
                InvalidOperationException.class,
                () -> receiptService.generateReceipt("PAY002")
        );
    }
}