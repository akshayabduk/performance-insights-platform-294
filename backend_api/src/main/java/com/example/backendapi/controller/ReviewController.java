package com.example.backendapi.controller;

import com.example.backendapi.dto.ReviewRequest;
import com.example.backendapi.model.Review;
import com.example.backendapi.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Endpoints to manage performance reviews.
 */
@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Reviews", description = "Performance reviews CRUD")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) { this.service = service; }

    // PUBLIC_INTERFACE
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Create review", description = "Create a performance review (admin only).")
    public ResponseEntity<Review> create(@Valid @RequestBody ReviewRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update review", description = "Update a performance review (admin only).")
    public ResponseEntity<Review> update(@PathVariable String id, @Valid @RequestBody ReviewRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Get review", description = "Get a review by ID.")
    public ResponseEntity<Review> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "List employee reviews", description = "List all reviews for an employee.")
    public ResponseEntity<List<Review>> listByEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(service.listByEmployee(employeeId));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete review", description = "Delete a review by ID (admin only).")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
