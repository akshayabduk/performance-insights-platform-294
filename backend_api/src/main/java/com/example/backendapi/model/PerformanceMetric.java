package com.example.backendapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * PUBLIC_INTERFACE
 * Performance metric entity to record quantitative measures over time.
 */
@Document(collection = "metrics")
public class PerformanceMetric {

    @Id
    private String id;

    private String employeeId;
    private MetricType metricType;
    private double score;
    private Instant timestamp;
    private String note;

    public PerformanceMetric() {}

    public PerformanceMetric(String employeeId, MetricType metricType, double score, Instant timestamp, String note) {
        this.employeeId = employeeId;
        this.metricType = metricType;
        this.score = score;
        this.timestamp = timestamp;
        this.note = note;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getEmployeeId() { return employeeId; }
    public MetricType getMetricType() { return metricType; }
    public double getScore() { return score; }
    public Instant getTimestamp() { return timestamp; }
    public String getNote() { return note; }

    public void setId(String id) { this.id = id; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setMetricType(MetricType metricType) { this.metricType = metricType; }
    public void setScore(double score) { this.score = score; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public void setNote(String note) { this.note = note; }
}
