package com.parishay.healthhub.service;

import com.parishay.healthhub.entity.PayrollRecord;
import com.parishay.healthhub.repository.PayrollRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {

    private final PayrollRecordRepository payrollRecordRepository;

    public PayrollService(PayrollRecordRepository payrollRecordRepository) {
        this.payrollRecordRepository = payrollRecordRepository;
    }

    // Create payroll record (with basic net salary calc)
    public PayrollRecord create(PayrollRecord payrollRecord) {
        if (payrollRecord.getPaymentDate() == null) {
            payrollRecord.setPaymentDate(LocalDate.now());
        }
        if (payrollRecord.getDeductions() == null) {
            payrollRecord.setDeductions(0.0);
        }
        if (payrollRecord.getBasicSalary() == null) {
            payrollRecord.setBasicSalary(0.0);
        }

        // Prevent duplicate payroll for same employee + month
        if (payrollRecordRepository.existsByEmployeeIdAndPayMonth(
                payrollRecord.getEmployeeId(),
                payrollRecord.getPayMonth()
        )) {
            throw new RuntimeException("Payroll already exists for this employee and month");
        }

        double net = payrollRecord.getBasicSalary() - payrollRecord.getDeductions();
        payrollRecord.setNetSalary(net);

        return payrollRecordRepository.save(payrollRecord);
    }

    public List<PayrollRecord> getAll() {
        return payrollRecordRepository.findAll();
    }

    public Optional<PayrollRecord> getById(Long id) {
        return payrollRecordRepository.findById(id);
    }

    public PayrollRecord update(Long id, PayrollRecord updated) {
        return payrollRecordRepository.findById(id)
                .map(p -> {
                    p.setEmployeeId(updated.getEmployeeId());
                    p.setEmployeeName(updated.getEmployeeName());
                    p.setPayMonth(updated.getPayMonth());
                    p.setBasicSalary(updated.getBasicSalary());
                    p.setPresentDays(updated.getPresentDays());
                    p.setAbsentDays(updated.getAbsentDays());
                    p.setDeductions(updated.getDeductions());

                    double basic = updated.getBasicSalary() != null ? updated.getBasicSalary() : 0.0;
                    double ded = updated.getDeductions() != null ? updated.getDeductions() : 0.0;
                    p.setNetSalary(basic - ded);

                    p.setPaymentDate(updated.getPaymentDate());
                    p.setPaymentMethod(updated.getPaymentMethod());
                    p.setRemarks(updated.getRemarks());
                    return payrollRecordRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Payroll record not found with id " + id));
    }

    public void delete(Long id) {
        payrollRecordRepository.deleteById(id);
    }

    public List<PayrollRecord> getByEmployeeId(Long employeeId) {
        return payrollRecordRepository.findByEmployeeId(employeeId);
    }

    public List<PayrollRecord> getByPayMonth(String payMonth) {
        return payrollRecordRepository.findByPayMonth(payMonth);
    }
}