package com.example.backendapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * PUBLIC_INTERFACE
 * Request DTO for creating/updating employees.
 */
public class EmployeeRequest {
    @NotBlank
    @Schema(description = "Full name")
    private String fullName;

    @NotBlank
    @Schema(description = "Department name")
    private String department;

    @NotBlank
    @Schema(description = "Job title")
    private String title;

    @Schema(description = "Manager's Employee ID (optional)")
    private String managerId;

    @Schema(description = "Status of employee (ACTIVE/INACTIVE/ON_LEAVE)")
    private String status = "ACTIVE";

    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }
    public String getTitle() { return title; }
    public String getManagerId() { return managerId; }
    public String getStatus() { return status; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setDepartment(String department) { this.department = department; }
    public void setTitle(String title) { this.title = title; }
    public void setManagerId(String managerId) { this.managerId = managerId; }
    public void setStatus(String status) { this.status = status; }
}
