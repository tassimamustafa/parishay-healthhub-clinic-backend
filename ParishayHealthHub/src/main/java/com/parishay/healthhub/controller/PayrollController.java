package com.parishay.healthhub.controller;

import com.parishay.healthhub.entity.PayrollRecord;
import com.parishay.healthhub.service.PayrollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payrolls")
@CrossOrigin(origins = "*")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // Create payroll record
    @PostMapping
    public ResponseEntity<PayrollRecord> create(@RequestBody PayrollRecord payrollRecord) {
        PayrollRecord saved = payrollService.create(payrollRecord);
        return ResponseEntity.ok(saved);
    }

    // Get all payrolls
    @GetMapping
    public ResponseEntity<List<PayrollRecord>> getAll() {
        return ResponseEntity.ok(payrollService.getAll());
    }

    // Get one
    @GetMapping("/{id}")
    public ResponseEntity<PayrollRecord> getById(@PathVariable Long id) {
        return payrollService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<PayrollRecord> update(@PathVariable Long id,
                                                @RequestBody PayrollRecord payrollRecord) {
        PayrollRecord updated = payrollService.update(id, payrollRecord);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        payrollService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // All payrolls of one employee
    @GetMapping("/by-employee")
    public ResponseEntity<List<PayrollRecord>> getByEmployee(
            @RequestParam("employeeId") Long employeeId) {
        return ResponseEntity.ok(payrollService.getByEmployeeId(employeeId));
    }

    // All payrolls of a month (e.g. 2026-04)
    @GetMapping("/by-month")
    public ResponseEntity<List<PayrollRecord>> getByMonth(
            @RequestParam("payMonth") String payMonth) {
        return ResponseEntity.ok(payrollService.getByPayMonth(payMonth));
    }
}