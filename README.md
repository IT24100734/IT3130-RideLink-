# RideLink - Driver & Vehicle Service (Member 2)

## Responsibility
Driver operational profile, vehicle details, availability, location/service area, eligible drivers.

## Run
```bash
./mvnw spring-boot:run
```
Swagger: http://localhost:8082/swagger-ui.html

## Key Endpoints
- POST /api/drivers
- GET  /api/drivers/{id}
- PUT  /api/drivers/{id}/availability
- GET  /api/drivers/available?serviceArea=Colombo
