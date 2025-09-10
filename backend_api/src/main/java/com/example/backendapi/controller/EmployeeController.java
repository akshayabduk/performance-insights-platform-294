package com.example.backendapi.controller;

import com.example.backendapi.dto.EmployeeRequest;
import com.example.backendapi.model.Employee;
import com.example.backendapi.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Endpoints to manage employees.
 */
@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employees", description = "Employee CRUD")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) { this.service = service; }

    // PUBLIC_INTERFACE
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Create employee", description = "Create a new employee (admin only).")
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update employee", description = "Update an employee by ID (admin only).")
    public ResponseEntity<Employee> update(@PathVariable String id, @Valid @RequestBody EmployeeRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Get employee", description = "Get an employee by ID.")
    public ResponseEntity<Employee> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "List employees", description = "List all employees.")
    public ResponseEntity<List<Employee>> list() {
        return ResponseEntity.ok(service.list());
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete employee", description = "Delete an employee by ID (admin only).")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
