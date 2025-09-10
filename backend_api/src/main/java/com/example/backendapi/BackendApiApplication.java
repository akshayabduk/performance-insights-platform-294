package com.example.backendapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PUBLIC_INTERFACE
 * The Spring Boot application entry point for the Employee Performance Insights Backend API.
 * This app exposes REST endpoints for authentication, employee/metrics/reviews management,
 * analytics, reports, and notifications, integrated with MongoDB and secured with JWT.
 */
@SpringBootApplication
public class BackendApiApplication {

    // PUBLIC_INTERFACE
    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }
}
