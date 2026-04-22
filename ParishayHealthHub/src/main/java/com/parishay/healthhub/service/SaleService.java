package com.parishay.healthhub.service;

import com.parishay.healthhub.entity.Sale;
import com.parishay.healthhub.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    // Basic CRUD
    public Sale create(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getById(Long id) {
        return saleRepository.findById(id);
    }

    public Sale update(Long id, Sale updated) {
        return saleRepository.findById(id)
                .map(sale -> {
                    sale.setDateTime(updated.getDateTime());
                    sale.setAmount(updated.getAmount());
                    sale.setType(updated.getType());
                    sale.setDescription(updated.getDescription());
                    sale.setDoctorName(updated.getDoctorName());
                    sale.setPaymentMethod(updated.getPaymentMethod());
                    return saleRepository.save(sale);
                })
                .orElseThrow(() -> new RuntimeException("Sale not found with id " + id));
    }

    public void delete(Long id) {
        saleRepository.deleteById(id);
    }

    // 1) Aaj ki total + date range total

    public Double getTodayTotal() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);
        return saleRepository.getTotalAmountBetween(start, end);
    }

    public Double getTotalBetween(LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);
        return saleRepository.getTotalAmountBetween(start, end);
    }

    public List<Sale> getBetween(LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);
        return saleRepository.findByDateTimeBetween(start, end);
    }

    // 2) Doctor wise list + total

    public List<Sale> getByDoctorName(String doctorName) {
        return saleRepository.findByDoctorName(doctorName);
    }

    public Double getTotalByDoctor(String doctorName) {
        return saleRepository.getTotalByDoctor(doctorName);
    }

    // 3) Payment method wise list + total

    public List<Sale> getByPaymentMethod(String paymentMethod) {
        return saleRepository.findByPaymentMethod(paymentMethod);
    }

    public Double getTotalByPaymentMethod(String paymentMethod) {
        return saleRepository.getTotalByPaymentMethod(paymentMethod);
    }
}
