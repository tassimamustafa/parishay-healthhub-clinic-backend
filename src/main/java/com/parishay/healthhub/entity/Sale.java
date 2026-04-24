package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Date + time of sale
    private LocalDateTime dateTime;

    // Amount of sale
    private Double amount;

    // Consultation, Medicine, Lab Test, Procedure, etc.
    private String type;

    // Short note
    private String description;

    // Which doctor (optional simple text for now)
    private String doctorName;

    // Cash, Card, Online, etc.
    private String paymentMethod;

    public Sale() {
    }

    public Sale(Long id, LocalDateTime dateTime, Double amount, String type,
                String description, String doctorName, String paymentMethod) {
        this.id = id;
        this.dateTime = dateTime;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.doctorName = doctorName;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}