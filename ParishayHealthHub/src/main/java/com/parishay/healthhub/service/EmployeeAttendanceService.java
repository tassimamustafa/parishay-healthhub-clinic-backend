package com.parishay.healthhub.service;

import com.parishay.healthhub.entity.EmployeeAttendance;
import com.parishay.healthhub.repository.EmployeeAttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeAttendanceService {

    private final EmployeeAttendanceRepository attendanceRepository;

    public EmployeeAttendanceService(EmployeeAttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Basic CRUD

    public EmployeeAttendance create(EmployeeAttendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<EmployeeAttendance> getAll() {
        return attendanceRepository.findAll();
    }

    public Optional<EmployeeAttendance> getById(Long id) {
        return attendanceRepository.findById(id);
    }

    public EmployeeAttendance update(Long id, EmployeeAttendance updated) {
        return attendanceRepository.findById(id)
                .map(a -> {
                    a.setEmployeeName(updated.getEmployeeName());
                    a.setDate(updated.getDate());
                    a.setStatus(updated.getStatus());
                    a.setNote(updated.getNote());
                    return attendanceRepository.save(a);
                })
                .orElseThrow(() -> new RuntimeException("Attendance not found with id " + id));
    }

    public void delete(Long id) {
        attendanceRepository.deleteById(id);
    }

    // Reports / Queries

    // 1) Aaj ki attendance (ya given date)
    public List<EmployeeAttendance> getByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    // 2) Employee + date range attendance list
    public List<EmployeeAttendance> getByEmployeeAndRange(String employeeName,
                                                          LocalDate startDate,
                                                          LocalDate endDate) {
        return attendanceRepository.findByEmployeeNameAndDateBetween(
                employeeName, startDate, endDate
        );
    }

    // 3) Present / Absent counts
    public Long getPresentCount(String employeeName,
                                LocalDate startDate,
                                LocalDate endDate) {
        return attendanceRepository.countPresentDays(employeeName, startDate, endDate);
    }

    public Long getAbsentCount(String employeeName,
                               LocalDate startDate,
                               LocalDate endDate) {
        return attendanceRepository.countAbsentDays(employeeName, startDate, endDate);
    }
}
