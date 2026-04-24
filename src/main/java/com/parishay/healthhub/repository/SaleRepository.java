package com.parishay.healthhub.repository;

import com.parishay.healthhub.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    // 1) Date range list
    List<Sale> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    // 1) Date range total
    @Query("SELECT COALESCE(SUM(s.amount), 0) FROM Sale s WHERE s.dateTime BETWEEN :start AND :end")
    Double getTotalAmountBetween(@Param("start") LocalDateTime start,
                                 @Param("end") LocalDateTime end);

    // 2) Doctor wise list
    List<Sale> findByDoctorName(String doctorName);

    // 2) Doctor wise total
    @Query("SELECT COALESCE(SUM(s.amount), 0) FROM Sale s WHERE s.doctorName = :doctorName")
    Double getTotalByDoctor(@Param("doctorName") String doctorName);

    // 3) Payment method wise list
    List<Sale> findByPaymentMethod(String paymentMethod);

    // 3) Payment method wise total
    @Query("SELECT COALESCE(SUM(s.amount), 0) FROM Sale s WHERE s.paymentMethod = :paymentMethod")
    Double getTotalByPaymentMethod(@Param("paymentMethod") String paymentMethod);
}
