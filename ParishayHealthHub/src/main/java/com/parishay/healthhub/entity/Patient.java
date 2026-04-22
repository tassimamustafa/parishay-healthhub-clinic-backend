package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic info
    private String fullName;
    private String gender;          // Male / Female / Other
    private Integer age;

    // Contact
    private String phone;
    private String address;

    // Optional: for clinic records
    private String cnic;            // or national ID
    private String city;

    // When patient was first registered
    private LocalDate registrationDate;

    public Patient() {
    }

    public Patient(Long id, String fullName, String gender, Integer age,
                   String phone, String address, String cnic,
                   String city, LocalDate registrationDate) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.cnic = cnic;
        this.city = city;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCnic() {
        return cnic;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}