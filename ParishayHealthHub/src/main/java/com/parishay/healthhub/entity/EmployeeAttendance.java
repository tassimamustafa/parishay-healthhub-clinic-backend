package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_attendance")
public class EmployeeAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Simple: employee name text (later we can link with Employee entity)
    private String employeeName;

    // Attendance date
    private LocalDate date;

    // Present / Absent / Leave
    private String status;

    // Optional: small note (late, half-day, reason, etc.)
    private String note;

    public EmployeeAttendance() {
    }

    public EmployeeAttendance(Long id, String employeeName, LocalDate date,
                              String status, String note) {
        this.id = id;
        this.employeeName = employeeName;
        this.date = date;
        this.status = status;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
