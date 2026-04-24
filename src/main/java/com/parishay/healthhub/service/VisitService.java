package com.parishay.healthhub.service;

import com.parishay.healthhub.entity.Visit;
import com.parishay.healthhub.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    // Create
    public Visit create(Visit visit) {
        if (visit.getVisitDateTime() == null) {
            visit.setVisitDateTime(LocalDateTime.now());
        }
        return visitRepository.save(visit);
    }

    // Read all
    public List<Visit> getAll() {
        return visitRepository.findAll();
    }

    // Read one
    public Optional<Visit> getById(Long id) {
        return visitRepository.findById(id);
    }

    // Update
    public Visit update(Long id, Visit updated) {
        return visitRepository.findById(id)
                .map(v -> {
                    v.setAppointmentId(updated.getAppointmentId());
                    v.setPatientId(updated.getPatientId());
                    v.setDoctorId(updated.getDoctorId());
                    v.setPatientName(updated.getPatientName());
                    v.setDoctorName(updated.getDoctorName());
                    v.setVisitDateTime(updated.getVisitDateTime());
                    v.setSymptoms(updated.getSymptoms());
                    v.setDiagnosis(updated.getDiagnosis());
                    v.setMedicines(updated.getMedicines());
                    v.setAdvice(updated.getAdvice());
                    return visitRepository.save(v);
                })
                .orElseThrow(() -> new RuntimeException("Visit not found with id " + id));
    }

    // Delete
    public void delete(Long id) {
        visitRepository.deleteById(id);
    }

    // Filters

    public List<Visit> getByPatientId(Long patientId) {
        return visitRepository.findByPatientId(patientId);
    }

    public List<Visit> getByDoctorId(Long doctorId) {
        return visitRepository.findByDoctorId(doctorId);
    }

    public List<Visit> getByAppointmentId(Long appointmentId) {
        return visitRepository.findByAppointmentId(appointmentId);
    }
}