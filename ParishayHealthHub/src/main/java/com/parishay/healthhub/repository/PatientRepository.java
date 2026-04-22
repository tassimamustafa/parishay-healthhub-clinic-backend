package com.parishay.healthhub.repository;

import com.parishay.healthhub.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Search by name containing text (case-insensitive can be added later)
    List<Patient> findByFullNameContainingIgnoreCase(String fullName);

    // Search by phone
    List<Patient> findByPhone(String phone);

    // Search by city
    List<Patient> findByCityIgnoreCase(String city);
}