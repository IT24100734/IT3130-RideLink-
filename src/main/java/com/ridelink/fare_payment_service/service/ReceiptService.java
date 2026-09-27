package com.ridelink.fare_payment_service.service;

import com.ridelink.fare_payment_service.exception.ResourceNotFoundException;
import com.ridelink.fare_payment_service.model.Payment;
import com.ridelink.fare_payment_service.model.PaymentStatus;
import com.ridelink.fare_payment_service.model.Receipt;
import com.ridelink.fare_payment_service.repository.PaymentRepository;
import com.ridelink.fare_payment_service.repository.ReceiptRepository;
import org.springframework.stereotype.Service;
import com.ridelink.fare_payment_service.exception.InvalidOperationException;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final PaymentRepository paymentRepository;

    public ReceiptService(
            ReceiptRepository receiptRepository,
            PaymentRepository paymentRepository) {

        this.receiptRepository = receiptRepository;
        this.paymentRepository = paymentRepository;
    }

    public Receipt generateReceipt(String paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found"));

        if (payment.getStatus() != PaymentStatus.SUCCESS) {
            throw new InvalidOperationException(
                    "Receipt cannot be generated for failed payment"
            );
        }

        Receipt receipt = new Receipt();

        receipt.setPaymentId(payment.getId());
        receipt.setRideId(payment.getRideId());
        receipt.setAmount(payment.getAmount());
        receipt.setPaymentMethod(payment.getPaymentMethod());
        receipt.setTransactionReference(
                payment.getTransactionReference()
        );

        return receiptRepository.save(receipt);
    }

    public Receipt getReceiptById(String receiptId) {

        return receiptRepository.findById(receiptId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Receipt not found"));
    }
}