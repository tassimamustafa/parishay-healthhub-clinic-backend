package com.parishay.healthhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                 // system generated primary key

    // CNIC / ID like 35301-6765713-2
    @NotBlank(message = "CNIC is required")
    @Size(max = 20, message = "CNIC must be at most 20 characters")
    private String cnic;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    // Mobile no like +92 343 867 8697
    @NotBlank(message = "Mobile number is required")
    @Size(max = 20, message = "Mobile number must be at most 20 characters")
    private String mobileNo;

    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    // Just city name
    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be at most 255 characters")
    private String address;

    // for payroll module
    @Positive(message = "Basic monthly salary must be positive")
    private Double basicMonthlySalary;

    // Active / Inactive
    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status must be at most 20 characters")
    private String status;

    // Office Boy, Helpdesk, Doctor, Receptionist etc.
    @NotBlank(message = "Role is required")
    @Size(max = 50, message = "Role must be at most 50 characters")
    private String role;

    @Positive(message = "Salary must be positive")
    private Double salary;

    // Duty time: day/month/year + time
    private LocalDateTime dutyTime;

    public Employee() {
    }

    public Employee(Long id, String cnic, String firstName, String lastName,
                    String mobileNo, String email, String address,
                    String status, String role, Double salary,
                    LocalDateTime dutyTime) {
        this.id = id;
        this.cnic = cnic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.address = address;
        this.status = status;
        this.role = role;
        this.salary = salary;
        this.dutyTime = dutyTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDateTime getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(LocalDateTime dutyTime) {
        this.dutyTime = dutyTime;
    }

    public Double getBasicMonthlySalary() {
        return basicMonthlySalary;
    }

    public void setBasicMonthlySalary(Double basicMonthlySalary) {
        this.basicMonthlySalary = basicMonthlySalary;
    }
}