package com.parishay.healthhub.repository;

import com.parishay.healthhub.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {

    // 1) Aaj ki attendance (ya kisi particular date ki)
    List<EmployeeAttendance> findByDate(LocalDate date);

    // 2) Kisi employee ki date range attendance
    List<EmployeeAttendance> findByEmployeeNameAndDateBetween(
            String employeeName,
            LocalDate startDate,
            LocalDate endDate
    );

    // 3) Count present days for employee in date range
    @Query("SELECT COUNT(ea) FROM EmployeeAttendance ea " +
           "WHERE ea.employeeName = :employeeName " +
           "AND ea.date BETWEEN :startDate AND :endDate " +
           "AND ea.status = 'Present'")
    Long countPresentDays(
            @Param("employeeName") String employeeName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    // 4) Count absent days for employee in date range
    @Query("SELECT COUNT(ea) FROM EmployeeAttendance ea " +
           "WHERE ea.employeeName = :employeeName " +
           "AND ea.date BETWEEN :startDate AND :endDate " +
           "AND ea.status = 'Absent'")
    Long countAbsentDays(
            @Param("employeeName") String employeeName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
