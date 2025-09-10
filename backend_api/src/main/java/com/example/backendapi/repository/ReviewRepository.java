package com.example.backendapi.repository;

import com.example.backendapi.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Repository for performance reviews.
 */
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByEmployeeId(String employeeId);
}
