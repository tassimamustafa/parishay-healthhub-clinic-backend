package com.parishay.healthhub.service;

import com.parishay.healthhub.entity.Appointment;
import com.parishay.healthhub.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // --------- Basic CRUD ---------

    public Appointment create(Appointment appointment) {
        if (appointment.getStatus() == null || appointment.getStatus().isEmpty()) {
            appointment.setStatus("BOOKED");
        }
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment update(Long id, Appointment updated) {
        return appointmentRepository.findById(id)
                .map(appt -> {
                    appt.setPatientId(updated.getPatientId());
                    appt.setDoctorId(updated.getDoctorId());
                    appt.setPatientName(updated.getPatientName());
                    appt.setDoctorName(updated.getDoctorName());
                    appt.setAppointmentDateTime(updated.getAppointmentDateTime());
                    appt.setStatus(updated.getStatus());
                    appt.setReason(updated.getReason());
                    appt.setSaleId(updated.getSaleId());
                    return appointmentRepository.save(appt);
                })
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    // --------- Queries / Filters ---------

    public List<Appointment> getByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getByDoctorAndDate(Long doctorId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(
                doctorId, start, end
        );
    }

    public List<Appointment> getByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }

    // --------- Link Appointment to Sale ---------

    public Appointment linkSale(Long appointmentId, Long saleId) {
        return appointmentRepository.findById(appointmentId)
                .map(appt -> {
                    appt.setSaleId(saleId);
                    return appointmentRepository.save(appt);
                })
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + appointmentId));
    }
}