package com.example.backendapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * PUBLIC_INTERFACE
 * Basic health and information endpoints for the backend service.
 */
@RestController
@Tag(name = "System", description = "System info and health endpoints")
public class HelloController {

    // PUBLIC_INTERFACE
    @GetMapping("/")
    @Operation(summary = "Welcome endpoint", description = "Returns a welcome message to indicate the API is reachable.")
    public String hello() {
        return "Hello, Spring Boot! Welcome to Performance Insights Backend API";
    }

    // PUBLIC_INTERFACE
    @GetMapping("/docs")
    @Operation(summary = "API Documentation", description = "Redirects to Swagger UI for interactive documentation.")
    public RedirectView docs() {
        return new RedirectView("/swagger-ui.html");
    }

    // PUBLIC_INTERFACE
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status.")
    public String health() {
        return "OK";
    }

    // PUBLIC_INTERFACE
    @GetMapping("/api/info")
    @Operation(summary = "Application info", description = "Returns application information.")
    public String info() {
        return "Spring Boot Application: Performance Insights Backend API";
    }
}
