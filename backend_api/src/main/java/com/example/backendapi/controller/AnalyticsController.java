package com.example.backendapi.controller;

import com.example.backendapi.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * PUBLIC_INTERFACE
 * Analytics endpoints providing AI/heuristic-based insights across the organization and per employee.
 */
@RestController
@RequestMapping("/api/analytics")
@Tag(name = "Analytics", description = "AI-driven analytics and insights")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) { this.analyticsService = analyticsService; }

    // PUBLIC_INTERFACE
    @GetMapping("/insights")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Overall insights", description = "Compute overall insights over recent days, including averages and top performers.")
    public ResponseEntity<Object> overall(@RequestParam(defaultValue = "30") int days) {
        return ResponseEntity.ok(analyticsService.overallInsights(days));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/employee/{employeeId}/insights")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Employee insights", description = "Compute insights for a given employee over recent days including averages and trend.")
    public ResponseEntity<Object> employee(@PathVariable String employeeId, @RequestParam(defaultValue = "90") int days) {
        return ResponseEntity.ok(analyticsService.employeeInsights(employeeId, days));
    }
}
