package com.example.backendapi.service;

import com.example.backendapi.dto.MetricRequest;
import com.example.backendapi.model.MetricType;
import com.example.backendapi.model.PerformanceMetric;
import com.example.backendapi.repository.MetricRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * PUBLIC_INTERFACE
 * Service managing performance metrics and chart data.
 */
@Service
public class MetricService {

    private final MetricRepository repo;

    public MetricService(MetricRepository repo) {
        this.repo = repo;
    }

    // PUBLIC_INTERFACE
    public PerformanceMetric create(MetricRequest req) {
        PerformanceMetric m = new PerformanceMetric(req.getEmployeeId(), req.getMetricType(), req.getScore(), Instant.now(), req.getNote());
        return repo.save(m);
    }

    // PUBLIC_INTERFACE
    public PerformanceMetric update(String id, MetricRequest req) {
        PerformanceMetric m = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Metric not found"));
        m.setEmployeeId(req.getEmployeeId());
        m.setMetricType(req.getMetricType());
        m.setScore(req.getScore());
        m.setNote(req.getNote());
        // keep timestamp
        return repo.save(m);
    }

    // PUBLIC_INTERFACE
    public void delete(String id) {
        repo.deleteById(id);
    }

    // PUBLIC_INTERFACE
    public PerformanceMetric get(String id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Metric not found"));
    }

    // PUBLIC_INTERFACE
    public List<PerformanceMetric> listByEmployee(String employeeId) {
        var list = repo.findByEmployeeId(employeeId);
        list.sort(Comparator.comparing(PerformanceMetric::getTimestamp));
        return list;
    }

    // PUBLIC_INTERFACE
    public List<PerformanceMetric> listByEmployeeAndType(String employeeId, MetricType type) {
        var list = repo.findByEmployeeIdAndMetricType(employeeId, type);
        list.sort(Comparator.comparing(PerformanceMetric::getTimestamp));
        return list;
    }

    // PUBLIC_INTERFACE
    public List<PerformanceMetric> getTrendWindow(String employeeId, MetricType type, int days) {
        Instant end = Instant.now();
        Instant start = end.minus(days, ChronoUnit.DAYS);
        var list = repo.findByEmployeeIdAndMetricTypeAndTimestampBetween(employeeId, type, start, end);
        list.sort(Comparator.comparing(PerformanceMetric::getTimestamp));
        return list;
    }
}
