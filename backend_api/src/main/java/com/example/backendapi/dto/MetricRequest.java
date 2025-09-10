package com.example.backendapi.dto;

import com.example.backendapi.model.MetricType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * PUBLIC_INTERFACE
 * Request DTO for creating/updating a metric.
 */
public class MetricRequest {

    @NotBlank
    @Schema(description = "Employee ID")
    private String employeeId;

    @NotNull
    @Schema(description = "Metric type")
    private MetricType metricType;

    @Schema(description = "Score value")
    private double score;

    @Schema(description = "Optional note")
    private String note;

    public String getEmployeeId() { return employeeId; }
    public MetricType getMetricType() { return metricType; }
    public double getScore() { return score; }
    public String getNote() { return note; }

    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setMetricType(MetricType metricType) { this.metricType = metricType; }
    public void setScore(double score) { this.score = score; }
    public void setNote(String note) { this.note = note; }
}
