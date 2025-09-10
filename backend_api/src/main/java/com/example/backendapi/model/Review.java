package com.example.backendapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * PUBLIC_INTERFACE
 * Performance review entity including qualitative feedback and rating.
 */
@Document(collection = "reviews")
public class Review {

    @Id
    private String id;

    private String employeeId;
    private String reviewerName;
    private double rating; // 0-5
    private String comments;
    private Instant timestamp;

    public Review() {}

    public Review(String employeeId, String reviewerName, double rating, String comments, Instant timestamp) {
        this.employeeId = employeeId;
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comments = comments;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getEmployeeId() { return employeeId; }
    public String getReviewerName() { return reviewerName; }
    public double getRating() { return rating; }
    public String getComments() { return comments; }
    public Instant getTimestamp() { return timestamp; }

    public void setId(String id) { this.id = id; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }
    public void setRating(double rating) { this.rating = rating; }
    public void setComments(String comments) { this.comments = comments; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
