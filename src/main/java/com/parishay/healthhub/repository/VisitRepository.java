package com.parishay.healthhub.repository;

import com.parishay.healthhub.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    // All visits of a patient
    List<Visit> findByPatientId(Long patientId);

    // All visits of a doctor
    List<Visit> findByDoctorId(Long doctorId);

    // Visit of a specific appointment (usually 1)
    List<Visit> findByAppointmentId(Long appointmentId);
}