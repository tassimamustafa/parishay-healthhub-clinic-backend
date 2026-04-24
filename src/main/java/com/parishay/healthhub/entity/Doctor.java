package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String specialization;

    private String phone;

    private String email;

    private String city;

    private Double fees;

    private String status; // Active / Inactive

    private LocalDateTime dutyTime;

    public Doctor() {
    }

    public Doctor(Long id, String name, String specialization, String phone,
                  String email, String city, Double fees, String status,
                  LocalDateTime dutyTime) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.fees = fees;
        this.status = status;
        this.dutyTime = dutyTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(LocalDateTime dutyTime) {
        this.dutyTime = dutyTime;
    }
}