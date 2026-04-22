package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Patient (Patient table ka id)
    private Long patientId;

    // Link to Doctor (Doctor table ka id)
    private Long doctorId;

    // Optional: easy viewing ke liye names bhi store kar lete hain
    private String patientName;
    private String doctorName;

    // Appointment date + time
    private LocalDateTime appointmentDateTime;

    // Status: BOOKED / COMPLETED / CANCELLED / NO_SHOW
    private String status;

    // Reason / notes
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