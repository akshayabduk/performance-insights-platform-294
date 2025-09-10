package com.example.backendapi.service;

import com.example.backendapi.dto.EmployeeRequest;
import com.example.backendapi.model.Employee;
import com.example.backendapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * PUBLIC_INTERFACE
 * Employee CRUD service.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    // PUBLIC_INTERFACE
    public Employee create(EmployeeRequest req) {
        Employee e = new Employee(req.getFullName(), req.getDepartment(), req.getTitle(), req.getManagerId(), LocalDate.now(), req.getStatus());
        return repo.save(e);
    }

    // PUBLIC_INTERFACE
    public Employee update(String id, EmployeeRequest req) {
        Employee e = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Employee not found"));
        e.setFullName(req.getFullName());
        e.setDepartment(req.getDepartment());
        e.setTitle(req.getTitle());
        e.setManagerId(req.getManagerId());
        e.setStatus(req.getStatus());
        return repo.save(e);
    }

    // PUBLIC_INTERFACE
    public void delete(String id) {
        repo.deleteById(id);
    }

    // PUBLIC_INTERFACE
    public Employee get(String id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    // PUBLIC_INTERFACE
    public List<Employee> list() {
        return repo.findAll();
    }
}
