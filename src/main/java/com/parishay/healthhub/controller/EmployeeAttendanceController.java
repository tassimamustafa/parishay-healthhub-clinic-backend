package com.parishay.healthhub.controller;

import com.parishay.healthhub.entity.EmployeeAttendance;
import com.parishay.healthhub.service.EmployeeAttendanceService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class EmployeeAttendanceController {

    private final EmployeeAttendanceService attendanceService;

    public EmployeeAttendanceController(EmployeeAttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // ------------- Basic CRUD -------------

    // Mark / create attendance
    @PostMapping
    public ResponseEntity<EmployeeAttendance> create(
            @Valid @RequestBody EmployeeAttendance attendance) {
        EmployeeAttendance saved = attendanceService.create(attendance);
        return ResponseEntity.ok(saved);
    }

    // Get all attendance records
    @GetMapping
    public ResponseEntity<List<EmployeeAttendance>> getAll() {
        return ResponseEntity.ok(attendanceService.getAll());
    }

    // Get one by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAttendance> getById(@PathVariable Long id) {
        return attendanceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update attendance
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAttendance> update(@PathVariable Long id,
                                                     @Valid @RequestBody EmployeeAttendance attendance) {
        EmployeeAttendance updated = attendanceService.update(id, attendance);
        return ResponseEntity.ok(updated);
    }

    // Delete attendance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attendanceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ------------- Reports -------------

    // 1) Aaj ki attendance (ya specific date)
    @GetMapping("/date")
    public ResponseEntity<List<EmployeeAttendance>> getByDate(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceService.getByDate(date));
    }

    // 2) Employee + date range attendance list
    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeAttendance>> getByEmployeeAndRange(
            @RequestParam("name") String employeeName,
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(
                attendanceService.getByEmployeeAndRange(employeeName, startDate, endDate)
        );
    }

    // 3) Present / Absent count for employee in date range
    @GetMapping("/employee/count")
    public ResponseEntity<String> getPresentAbsentCounts(
            @RequestParam("name") String employeeName,
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Long present = attendanceService.getPresentCount(employeeName, startDate, endDate);
        Long absent = attendanceService.getAbsentCount(employeeName, startDate, endDate);

        String summary = "Employee: " + employeeName +
                ", Present: " + present +
                ", Absent: " + absent;

        return ResponseEntity.ok(summary);
    }
}