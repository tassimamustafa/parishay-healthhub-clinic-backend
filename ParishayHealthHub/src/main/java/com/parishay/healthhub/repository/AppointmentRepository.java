package com.parishay.healthhub.repository;

import com.parishay.healthhub.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Patient ke saare appointments
    List<Appointment> findByPatientId(Long patientId);

    // Doctor ke saare appointments
    List<Appointment> findByDoctorId(Long doctorId);

    // Kisi date-time range me doctor ke appointments
    List<Appointment> findByDoctorIdAndAppointmentDateTimeBetween(
            Long doctorId,
            LocalDateTime start,
            LocalDateTime end
    );

    // Status wise list (Booked/Completed/Cancelled)
    List<Appointment> findByStatus(String status);
}