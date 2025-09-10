package com.example.backendapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * PUBLIC_INTERFACE
 * Request DTO for creating/updating a review.
 */
public class ReviewRequest {

    @NotBlank
    @Schema(description = "Employee ID")
    private String employeeId;

    @NotBlank
    @Schema(description = "Reviewer name")
    private String reviewerName;

    @NotNull
    @Schema(description = "Rating (0-5)")
    private Double rating;

    @Schema(description = "Comments")
    private String comments;

    public String getEmployeeId() { return employeeId; }
    public String getReviewerName() { return reviewerName; }
    public Double getRating() { return rating; }
    public String getComments() { return comments; }

    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }
    public void setRating(Double rating) { this.rating = rating; }
    public void setComments(String comments) { this.comments = comments; }
}
