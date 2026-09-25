package com.driver_service.repository;

import com.driver_service.model.AvailabilityStatus;
import com.driver_service.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends MongoRepository<Driver, String> {
    Optional<Driver> findByUserId(String userId);
    List<Driver> findByAvailabilityAndServiceArea(AvailabilityStatus availability, String serviceArea);
    List<Driver> findByAvailability(AvailabilityStatus availability);
    boolean existsByUserId(String userId);
}
