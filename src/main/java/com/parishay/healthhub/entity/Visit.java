package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Appointment, Patient, Doctor
    private Long appointmentId;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    // Readable names (convenience)
    @Size(max = 100, message = "Patient name must be at most 100 characters")
    private String patientName;

    @Size(max = 100, message = "Doctor name must be at most 100 characters")
    private String doctorName;

    // When visit happened
    @NotNull(message = "Visit date and time is required")
    private LocalDateTime visitDateTime;

    // Clinical details
    @NotBlank(message = "Symptoms are required")
    @Size(max = 1000, message = "Symptoms must be at most 1000 characters")
    private String symptoms;    // e.g. "Headache, nausea"

    @NotBlank(message = "Diagnosis is required")
    @Size(max = 1000, message = "Diagnosis must be at most 1000 characters")
    private String diagnosis;   // e.g. "Migraine"

    @Size(max = 1000, message = "Medicines must be at most 1000 characters")
    private String medicines;   // e.g. "Paracetamol 500mg BD for 3 days"

    @Size(max = 1000, message = "Advice must be at most 1000 characters")
    private String advice;      // e.g. "Drink water, rest, follow-up in 1 week"

    public Visit() {
    }

    public Visit(Long id, Long appointmentId, Long patientId, Long doctorId,
                 String patientName, String doctorName,
                 LocalDateTime visitDateTime,
                 String symptoms, String diagnosis,
                 String medicines, String advice) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.visitDateTime = visitDateTime;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.medicines = medicines;
        this.advice = advice;
    }

    public Long getId() {
        return id;
    }

    public Long getAppointmentId() {
        return appointmentId;
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

    public LocalDateTime getVisitDateTime() {
        return visitDateTime;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getMedicines() {
        return medicines;
    }

    public String getAdvice() {
        return advice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
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

    public void setVisitDateTime(LocalDateTime visitDateTime) {
        this.visitDateTime = visitDateTime;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}