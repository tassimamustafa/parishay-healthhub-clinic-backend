package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "payroll_records",
       uniqueConstraints = @UniqueConstraint(columnNames = {"employeeId", "payMonth"}))
public class PayrollRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to employee
    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotBlank(message = "Employee name is required")
    @Size(max = 100, message = "Employee name must be at most 100 characters")
    private String employeeName;   // convenience

    // Month for which salary is paid (e.g. "2026-04")
    @NotBlank(message = "Pay month is required")
    @Size(max = 7, message = "Pay month must be at most 7 characters (e.g. 2026-04)")
    private String payMonth;

    // Salary details
    @NotNull(message = "Basic salary is required")
    @Positive(message = "Basic salary must be positive")
    private Double basicSalary;    // snapshot from employee at that time

    private Integer presentDays;   // optional
    private Integer absentDays;    // optional

    @PositiveOrZero(message = "Deductions must be zero or positive")
    private Double deductions;     // manual total deduction

    @PositiveOrZero(message = "Net salary must be zero or positive")
    private Double netSalary;      // basicSalary - deductions

    // Payment info
    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    @NotBlank(message = "Payment method is required")
    @Size(max = 50, message = "Payment method must be at most 50 characters")
    private String paymentMethod;  // Cash / BankTransfer / etc.

    @Size(max = 255, message = "Remarks must be at most 255 characters")
    private String remarks;

    public PayrollRecord() {
    }

    public PayrollRecord(Long id, Long employeeId, String employeeName,
                         String payMonth, Double basicSalary,
                         Integer presentDays, Integer absentDays,
                         Double deductions, Double netSalary,
                         LocalDate paymentDate, String paymentMethod,
                         String remarks) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.payMonth = payMonth;
        this.basicSalary = basicSalary;
        this.presentDays = presentDays;
        this.absentDays = absentDays;
        this.deductions = deductions;
        this.netSalary = netSalary;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public Integer getPresentDays() {
        return presentDays;
    }

    public Integer getAbsentDays() {
        return absentDays;
    }

    public Double getDeductions() {
        return deductions;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setPresentDays(Integer presentDays) {
        this.presentDays = presentDays;
    }

    public void setAbsentDays(Integer absentDays) {
        this.absentDays = absentDays;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}