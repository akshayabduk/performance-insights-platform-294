package com.example.backendapi.service;

import com.example.backendapi.model.Employee;
import com.example.backendapi.model.MetricType;
import com.example.backendapi.model.PerformanceMetric;
import com.example.backendapi.repository.EmployeeRepository;
import com.example.backendapi.repository.MetricRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PUBLIC_INTERFACE
 * AI-driven analytics service. For now, uses heuristic/statistical calculations to produce insights.
 * This can be later swapped with a true ML/AI model integration.
 */
@Service
public class AnalyticsService {

    private final MetricRepository metricRepository;
    private final EmployeeRepository employeeRepository;

    public AnalyticsService(MetricRepository metricRepository, EmployeeRepository employeeRepository) {
        this.metricRepository = metricRepository;
        this.employeeRepository = employeeRepository;
    }

    // PUBLIC_INTERFACE
    public Map<String, Object> overallInsights(int days) {
        Instant end = Instant.now();
        Instant start = end.minus(days, ChronoUnit.DAYS);
        List<PerformanceMetric> metrics = metricRepository.findByTimestampBetween(start, end);

        Map<MetricType, Double> averages = Arrays.stream(MetricType.values()).collect(Collectors.toMap(
                mt -> mt,
                mt -> {
                    var filtered = metrics.stream().filter(m -> m.getMetricType() == mt).mapToDouble(PerformanceMetric::getScore).toArray();
                    return filtered.length == 0 ? 0.0 : Arrays.stream(filtered).average().orElse(0.0);
                }
        ));

        Map<String, Double> employeeAverages = new HashMap<>();
        for (Employee e : employeeRepository.findAll()) {
            var em = metrics.stream().filter(m -> m.getEmployeeId().equals(e.getId())).mapToDouble(PerformanceMetric::getScore).toArray();
            if (em.length > 0) {
                employeeAverages.put(e.getFullName(), Arrays.stream(em).average().orElse(0.0));
            }
        }

        List<Map.Entry<String, Double>> topPerformers = employeeAverages.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(5).toList();

        Map<String, Object> result = new HashMap<>();
        result.put("periodDays", days);
        result.put("metricAverages", averages);
        result.put("topPerformers", topPerformers);
        result.put("insightSummary", buildInsightSummary(averages, topPerformers));
        return result;
    }

    // PUBLIC_INTERFACE
    public Map<String, Object> employeeInsights(String employeeId, int days) {
        Instant end = Instant.now();
        Instant start = end.minus(days, ChronoUnit.DAYS);

        var metrics = metricRepository.findByEmployeeId(employeeId);
        metrics = metrics.stream().filter(m -> !m.getTimestamp().isBefore(start)).sorted(Comparator.comparing(PerformanceMetric::getTimestamp)).toList();

        Map<MetricType, Double> averages = new EnumMap<>(MetricType.class);
        for (MetricType type : MetricType.values()) {
            var arr = metrics.stream().filter(m -> m.getMetricType() == type).mapToDouble(PerformanceMetric::getScore).toArray();
            averages.put(type, arr.length == 0 ? 0.0 : Arrays.stream(arr).average().orElse(0.0));
        }

        String trend = computeTrend(metrics.stream().map(PerformanceMetric::getScore).mapToDouble(d -> d).toArray());

        Map<String, Object> result = new HashMap<>();
        result.put("employeeId", employeeId);
        result.put("periodDays", days);
        result.put("averages", averages);
        result.put("trend", trend);
        return result;
    }

    private String computeTrend(double[] values) {
        if (values == null || values.length < 2) return "INSUFFICIENT_DATA";
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;
        int n = values.length;
        for (int i = 0; i < n; i++) {
            sumX += i;
            sumY += values[i];
            sumXY += i * values[i];
            sumX2 += i * i;
        }
        double denominator = n * sumX2 - sumX * sumX;
        if (denominator == 0) return "STABLE";
        double slope = (n * sumXY - sumX * sumY) / denominator;
        if (slope > 0.05) return "IMPROVING";
        if (slope < -0.05) return "DECLINING";
        return "STABLE";
    }

    private String buildInsightSummary(Map<MetricType, Double> averages, List<Map.Entry<String, Double>> top) {
        String topNames = top.stream().map(Map.Entry::getKey).collect(Collectors.joining(", "));
        String bestMetric = averages.entrySet().stream().max(Map.Entry.comparingByValue()).map(e -> e.getKey().name()).orElse("N/A");
        return "Best average metric: " + bestMetric + ". Top performers: " + topNames + ".";
    }
}
