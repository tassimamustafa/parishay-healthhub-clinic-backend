package com.parishay.healthhub.controller;

import com.parishay.healthhub.entity.Patient;
import com.parishay.healthhub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // --------- Basic CRUD ---------

    // Create new patient
    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        Patient saved = patientService.create(patient);
        return ResponseEntity.ok(saved);
    }

    // Get all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok(patientService.getAll());
    }

    // Get one patient
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id) {
        return patientService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id,
                                          @RequestBody Patient patient) {
        Patient updated = patientService.update(id, patient);
        return ResponseEntity.ok(updated);
    }

    // Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --------- Search APIs ---------

    // Search by name (contains)
    @GetMapping("/search/by-name")
    public ResponseEntity<List<Patient>> searchByName(
            @RequestParam("name") String name) {
        return ResponseEntity.ok(patientService.searchByName(name));
    }

    // Search by phone
    @GetMapping("/search/by-phone")
    public ResponseEntity<List<Patient>> searchByPhone(
            @RequestParam("phone") String phone) {
        return ResponseEntity.ok(patientService.searchByPhone(phone));
    }

    // Search by city
    @GetMapping("/search/by-city")
    public ResponseEntity<List<Patient>> searchByCity(
            @RequestParam("city") String city) {
        return ResponseEntity.ok(patientService.searchByCity(city));
    }
}