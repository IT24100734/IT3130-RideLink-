# RideLink - Driver & Vehicle Service (Member 2)

## Responsibility
Driver operational profile, vehicle details, availability, location/service area, eligible drivers.

## Prerequisites
- Java 17 or 21 (e.g. OpenJDK 21)
- MongoDB (Local instance or Online MongoDB Atlas Cluster)

## How to Run

### Option A: Local MongoDB
```bash
JAVA_HOME=/opt/homebrew/opt/openjdk@21 ./mvnw spring-boot:run
```

### Option B: Online MongoDB Cluster (MongoDB Atlas)
Set the `MONGODB_URI` environment variable with your Atlas connection string:
```bash
MONGODB_URI="mongodb+srv://<username>:<password>@<cluster-name>.mongodb.net/ridelink_drivers?retryWrites=true&w=majority" \
JAVA_HOME=/opt/homebrew/opt/openjdk@21 ./mvnw spring-boot:run
```

## Swagger UI & OpenAPI Docs
- **Swagger UI:** http://localhost:8082/swagger-ui.html
- **OpenAPI v3 Spec:** http://localhost:8082/v3/api-docs

## Key Endpoints
- `POST /api/drivers` - Register driver operational profile + vehicle
- `GET /api/drivers/{id}` - Get driver profile by ID
- `GET /api/drivers/user/{userId}` - Get driver profile by User ID
- `PUT /api/drivers/{id}/availability` - Update availability & location
- `GET /api/drivers/available?serviceArea=Colombo` - Get eligible available drivers

