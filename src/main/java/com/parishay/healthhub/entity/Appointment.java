package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Patient (Patient table ka id)
    @NotNull(message = "Patient ID is required")
    private Long patientId;

    // Link to Doctor (Doctor table ka id)
    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    // Optional: easy viewing ke liye names bhi store kar lete hain
    @Size(max = 100, message = "Patient name must be at most 100 characters")
    private String patientName;

    @Size(max = 100, message = "Doctor name must be at most 100 characters")
    private String doctorName;

    // Appointment date + time
    @NotNull(message = "Appointment date and time is required")
    private LocalDateTime appointmentDateTime;

    // Status: BOOKED / COMPLETED / CANCELLED / NO_SHOW
    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status must be at most 20 characters")
    private String status;

    // Reason / notes
    @Size(max = 1000, message = "Reason must be at most 1000 characters")
    private String reason;

    // NEW: linked Sale record ka id (nullable)
    private Long saleId;

    public Appointment() {
    }

    public Appointment(Long id, Long patientId, Long doctorId,
                       String patientName, String doctorName,
                       LocalDateTime appointmentDateTime,
                       String status, String reason, Long saleId) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentDateTime = appointmentDateTime;
        this.status = status;
        this.reason = reason;
        this.saleId = saleId;
    }

    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}