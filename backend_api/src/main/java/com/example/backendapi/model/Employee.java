package com.example.backendapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * PUBLIC_INTERFACE
 * Employee entity describing an employee's profile.
 */
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    private String fullName;
    private String department;
    private String title;
    private String managerId;
    private LocalDate hireDate;
    private String status; // ACTIVE, INACTIVE, ON_LEAVE

    public Employee() {}

    public Employee(String fullName, String department, String title, String managerId, LocalDate hireDate, String status) {
        this.fullName = fullName;
        this.department = department;
        this.title = title;
        this.managerId = managerId;
        this.hireDate = hireDate;
        this.status = status;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }
    public String getTitle() { return title; }
    public String getManagerId() { return managerId; }
    public LocalDate getHireDate() { return hireDate; }
    public String getStatus() { return status; }

    public void setId(String id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setDepartment(String department) { this.department = department; }
    public void setTitle(String title) { this.title = title; }
    public void setManagerId(String managerId) { this.managerId = managerId; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    public void setStatus(String status) { this.status = status; }
}
