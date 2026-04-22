package com.parishay.healthhub.service;

import com.parishay.healthhub.entity.Patient;
import com.parishay.healthhub.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Create
    public Patient create(Patient patient) {
        if (patient.getRegistrationDate() == null) {
            patient.setRegistrationDate(LocalDate.now());
        }
        return patientRepository.save(patient);
    }

    // Read all
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    // Read one
    public Optional<Patient> getById(Long id) {
        return patientRepository.findById(id);
    }

    // Update
    public Patient update(Long id, Patient updated) {
        return patientRepository.findById(id)
                .map(p -> {
                    p.setFullName(updated.getFullName());
                    p.setGender(updated.getGender());
                    p.setAge(updated.getAge());
                    p.setPhone(updated.getPhone());
                    p.setAddress(updated.getAddress());
                    p.setCnic(updated.getCnic());
                    p.setCity(updated.getCity());
                    // registrationDate usually constant, but you can allow change:
                    p.setRegistrationDate(updated.getRegistrationDate());
                    return patientRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    // Delete
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    // Search helpers

    public List<Patient> searchByName(String name) {
        return patientRepository.findByFullNameContainingIgnoreCase(name);
    }

    public List<Patient> searchByPhone(String phone) {
        return patientRepository.findByPhone(phone);
    }

    public List<Patient> searchByCity(String city) {
        return patientRepository.findByCityIgnoreCase(city);
    }
}