package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Appointment, Patient, Doctor
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;

    // Readable names (convenience)
    private String patientName;
    private String doctorName;

    // When visit happened
    private LocalDateTime visitDateTime;

    // Clinical details
    private String symptoms;    // e.g. "Headache, nausea"
    private String diagnosis;   // e.g. "Migraine"
    private String medicines;   // e.g. "Paracetamol 500mg BD for 3 days"
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