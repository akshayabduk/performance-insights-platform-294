package com.example.backendapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * PUBLIC_INTERFACE
 * Configures OpenAPI metadata for the backend API.
 */
@Configuration
public class OpenApiConfig {

    // PUBLIC_INTERFACE
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Performance Insights API")
                        .version("0.1.0")
                        .description("REST API for authentication, employee performance data management, analytics, reporting, and notifications.")
                        .contact(new Contact().name("Performance Insights Team").email("support@example.com")))
                .addTagsItem(new Tag().name("Auth").description("User authentication and JWT"))
                .addTagsItem(new Tag().name("Users").description("User management for admin"))
                .addTagsItem(new Tag().name("Employees").description("Employee CRUD"))
                .addTagsItem(new Tag().name("Metrics").description("Performance metrics CRUD and chart data"))
                .addTagsItem(new Tag().name("Reviews").description("Performance reviews CRUD"))
                .addTagsItem(new Tag().name("Analytics").description("AI-driven analytics and insights"))
                .addTagsItem(new Tag().name("Reports").description("PDF/CSV report exports"))
                .addTagsItem(new Tag().name("Notifications").description("In-app notifications"))
                .externalDocs(new ExternalDocumentation()
                        .description("API Docs")
                        .url("/swagger-ui.html"));
    }
}
