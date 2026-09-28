# RideLink - Fare & Payment Service

## Overview

The Fare & Payment Service is responsible for fare estimation,
final fare calculation, simulated payment processing,
payment status management and receipt generation.

## Technology Stack

- Java 17
- Spring Boot
- MongoDB
- Maven
- Swagger / OpenAPI
- Postman
- JUnit 5
- Mockito

## Port

8084

## Database

ridelink_fare_payment_db

## Fare Calculation Rule

Estimated Fare:

Base Fare + (Distance × Rate Per KM)

Base Fare = Rs. 200  
Rate Per KM = Rs. 80

Final Fare:

Estimated Fare + Waiting Charge

Waiting Charge = Waiting Minutes × Rs. 20

## API Endpoints

### Fare

POST /api/fares/estimate  
POST /api/fares/final  
GET /api/fares/ride/{rideId}

### Payment

POST /api/payments  
GET /api/payments/{paymentId}  
GET /api/payments/ride/{rideId}

### Receipt

POST /api/receipts/payment/{paymentId}  
GET /api/receipts/{receiptId}

## Payment Status

- PENDING
- SUCCESS
- FAILED

## Run Application

```bash
./mvnw spring-boot:run