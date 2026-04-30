package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Date + time of sale
    @NotNull(message = "Sale date and time is required")
    private LocalDateTime dateTime;

    // Amount of sale
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    // Consultation, Medicine, Lab Test, Procedure, etc.
    @NotBlank(message = "Sale type is required")
    @Size(max = 50, message = "Sale type must be at most 50 characters")
    private String type;

    // Short note
    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    // Which doctor (optional simple text for now)
    @Size(max = 100, message = "Doctor name must be at most 100 characters")
    private String doctorName;

    // Cash, Card, Online, etc.
    @NotBlank(message = "Payment method is required")
    @Size(max = 50, message = "Payment method must be at most 50 characters")
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