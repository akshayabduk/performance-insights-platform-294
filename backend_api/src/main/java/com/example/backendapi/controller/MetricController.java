package com.example.backendapi.controller;

import com.example.backendapi.dto.ChartDataResponse;
import com.example.backendapi.dto.MetricRequest;
import com.example.backendapi.model.MetricType;
import com.example.backendapi.model.PerformanceMetric;
import com.example.backendapi.service.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Endpoints to manage performance metrics and fetch chart-ready data.
 */
@RestController
@RequestMapping("/api/metrics")
@Tag(name = "Metrics", description = "Performance metrics CRUD and trends")
public class MetricController {

    private final MetricService service;

    public MetricController(MetricService service) { this.service = service; }

    // PUBLIC_INTERFACE
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Create metric", description = "Create a performance metric record for an employee (admin only).")
    public ResponseEntity<PerformanceMetric> create(@Valid @RequestBody MetricRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update metric", description = "Update a performance metric (admin only).")
    public ResponseEntity<PerformanceMetric> update(@PathVariable String id, @Valid @RequestBody MetricRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Get metric", description = "Get a metric by ID.")
    public ResponseEntity<PerformanceMetric> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "List metrics by employee", description = "List all metrics for an employee.")
    public ResponseEntity<List<PerformanceMetric>> listByEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(service.listByEmployee(employeeId));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/trends")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Trend data", description = "Get chart-ready trend data for an employee and metric type over the given number of days.")
    public ResponseEntity<ChartDataResponse> trend(
            @RequestParam String employeeId,
            @RequestParam MetricType metricType,
            @RequestParam(defaultValue = "30") int days
    ) {
        var list = service.getTrendWindow(employeeId, metricType, days);
        var timestamps = list.stream().map(PerformanceMetric::getTimestamp).toList();
        var values = list.stream().map(PerformanceMetric::getScore).toList();
        return ResponseEntity.ok(new ChartDataResponse(timestamps, values));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete metric", description = "Delete a metric by ID (admin only).")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
