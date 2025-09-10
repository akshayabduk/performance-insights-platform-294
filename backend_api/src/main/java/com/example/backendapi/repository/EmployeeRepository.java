package com.example.backendapi.repository;

import com.example.backendapi.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * PUBLIC_INTERFACE
 * Repository for employees.
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
