package com.parishay.healthhub.repository;

import com.parishay.healthhub.entity.PayrollRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRecordRepository extends JpaRepository<PayrollRecord, Long> {

    // All payrolls of one employee
    List<PayrollRecord> findByEmployeeId(Long employeeId);

    // Payrolls of a specific month
    List<PayrollRecord> findByPayMonth(String payMonth);

    // Check duplicate (employee + month)
    boolean existsByEmployeeIdAndPayMonth(Long employeeId, String payMonth);
}