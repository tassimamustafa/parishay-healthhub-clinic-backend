package com.parishay.healthhub.controller;

import com.parishay.healthhub.entity.Visit;
import com.parishay.healthhub.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
@CrossOrigin(origins = "*")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    // --------- Basic CRUD ---------

    // Create new visit (prescription)
    @PostMapping
    public ResponseEntity<Visit> create(@RequestBody Visit visit) {
        Visit saved = visitService.create(visit);
        return ResponseEntity.ok(saved);
    }

    // Get all visits
    @GetMapping
    public ResponseEntity<List<Visit>> getAll() {
        return ResponseEntity.ok(visitService.getAll());
    }

    // Get one visit
    @GetMapping("/{id}")
    public ResponseEntity<Visit> getById(@PathVariable Long id) {
        return visitService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update visit
    @PutMapping("/{id}")
    public ResponseEntity<Visit> update(@PathVariable Long id,
                                        @RequestBody Visit visit) {
        Visit updated = visitService.update(id, visit);
        return ResponseEntity.ok(updated);
    }

    // Delete visit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        visitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --------- Filters ---------

    // All visits of a patient
    @GetMapping("/by-patient")
    public ResponseEntity<List<Visit>> getByPatient(
            @RequestParam("patientId") Long patientId) {
        return ResponseEntity.ok(visitService.getByPatientId(patientId));
    }

    // All visits of a doctor
    @GetMapping("/by-doctor")
    public ResponseEntity<List<Visit>> getByDoctor(
            @RequestParam("doctorId") Long doctorId) {
        return ResponseEntity.ok(visitService.getByDoctorId(doctorId));
    }

    // Visit(s) for a specific appointment
    @GetMapping("/by-appointment")
    public ResponseEntity<List<Visit>> getByAppointment(
            @RequestParam("appointmentId") Long appointmentId) {
        return ResponseEntity.ok(visitService.getByAppointmentId(appointmentId));
    }
}