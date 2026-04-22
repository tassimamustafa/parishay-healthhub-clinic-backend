package com.parishay.healthhub.service;

import com.parishay.healthhub.entity.Employee;
import com.parishay.healthhub.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Create new employee
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // Get single employee by id
    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update existing employee
    public Employee update(Long id, Employee updated) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    emp.setCnic(updated.getCnic());
                    emp.setFirstName(updated.getFirstName());
                    emp.setLastName(updated.getLastName());
                    emp.setMobileNo(updated.getMobileNo());
                    emp.setEmail(updated.getEmail());
                    emp.setAddress(updated.getAddress());
                    emp.setStatus(updated.getStatus());
                    emp.setRole(updated.getRole());
                    emp.setSalary(updated.getSalary());
                    emp.setDutyTime(updated.getDutyTime());
                    return employeeRepository.save(emp);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    // Delete employee
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}