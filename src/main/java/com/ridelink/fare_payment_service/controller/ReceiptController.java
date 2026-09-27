package com.ridelink.fare_payment_service.controller;

import com.ridelink.fare_payment_service.model.Receipt;
import com.ridelink.fare_payment_service.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipts")

public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/payment/{paymentId}")
    public ResponseEntity<Receipt> generateReceipt(
            @PathVariable String paymentId) {

        return ResponseEntity.ok(
                receiptService.generateReceipt(paymentId)
        );
    }

    @GetMapping("/{receiptId}")
    public ResponseEntity<Receipt> getReceipt(
            @PathVariable String receiptId) {

        return ResponseEntity.ok(
                receiptService.getReceiptById(receiptId)
        );
    }
}