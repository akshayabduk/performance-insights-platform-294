package com.example.backendapi.service;

import com.example.backendapi.dto.ReviewRequest;
import com.example.backendapi.model.Review;
import com.example.backendapi.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * PUBLIC_INTERFACE
 * Service for performance review CRUD.
 */
@Service
public class ReviewService {

    private final ReviewRepository repo;

    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    // PUBLIC_INTERFACE
    public Review create(ReviewRequest req) {
        Review r = new Review(req.getEmployeeId(), req.getReviewerName(), req.getRating(), req.getComments(), Instant.now());
        return repo.save(r);
    }

    // PUBLIC_INTERFACE
    public Review update(String id, ReviewRequest req) {
        Review r = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Review not found"));
        r.setEmployeeId(req.getEmployeeId());
        r.setReviewerName(req.getReviewerName());
        r.setRating(req.getRating());
        r.setComments(req.getComments());
        return repo.save(r);
    }

    // PUBLIC_INTERFACE
    public void delete(String id) {
        repo.deleteById(id);
    }

    // PUBLIC_INTERFACE
    public Review get(String id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Review not found"));
    }

    // PUBLIC_INTERFACE
    public List<Review> listByEmployee(String employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
