package com.example.backendapi.repository;

import com.example.backendapi.model.MetricType;
import com.example.backendapi.model.PerformanceMetric;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Repository for performance metrics.
 */
public interface MetricRepository extends MongoRepository<PerformanceMetric, String> {
    List<PerformanceMetric> findByEmployeeId(String employeeId);
    List<PerformanceMetric> findByEmployeeIdAndMetricType(String employeeId, MetricType type);
    List<PerformanceMetric> findByEmployeeIdAndMetricTypeAndTimestampBetween(String employeeId, MetricType type, Instant start, Instant end);
    List<PerformanceMetric> findByTimestampBetween(Instant start, Instant end);
}
