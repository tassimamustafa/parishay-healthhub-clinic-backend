package com.parishay.healthhub.service;

import com.parishay.healthhub.entity.Doctor;
import com.parishay.healthhub.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor update(Long id, Doctor updated) {
        return doctorRepository.findById(id)
                .map(doc -> {
                    doc.setName(updated.getName());
                    doc.setSpecialization(updated.getSpecialization());
                    doc.setPhone(updated.getPhone());
                    doc.setEmail(updated.getEmail());
                    doc.setCity(updated.getCity());
                    doc.setFees(updated.getFees());
                    doc.setStatus(updated.getStatus());
                    doc.setDutyTime(updated.getDutyTime());
                    return doctorRepository.save(doc);
                })
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }
}