package com.parishay.healthhub.controller;

import com.parishay.healthhub.entity.Sale;
import com.parishay.healthhub.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // ---------------- Basic CRUD ----------------

    // Create new sale
    @PostMapping
    public ResponseEntity<Sale> create(@Valid @RequestBody Sale sale) {
        Sale saved = saleService.create(sale);
        return ResponseEntity.ok(saved);
    }

    // Get all sales
    @GetMapping
    public ResponseEntity<List<Sale>> getAll() {
        return ResponseEntity.ok(saleService.getAll());
    }

    // Get one sale
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable Long id) {
        return saleService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update sale
    @PutMapping("/{id}")
    public ResponseEntity<Sale> update(@PathVariable Long id,
                                       @Valid @RequestBody Sale sale) {
        Sale updated = saleService.update(id, sale);
        return ResponseEntity.ok(updated);
    }

    // Delete sale
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------- Reports ----------------

    // 1) Aaj ki total sales
    @GetMapping("/today/total")
    public ResponseEntity<Double> getTodayTotal() {
        Double total = saleService.getTodayTotal();
        return ResponseEntity.ok(total);
    }

    // 1) Date range se total
    @GetMapping("/total")
    public ResponseEntity<Double> getTotalBetween(
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Double total = saleService.getTotalBetween(startDate, endDate);
        return ResponseEntity.ok(total);
    }

    // 4) Date range se full list
    @GetMapping("/between")
    public ResponseEntity<List<Sale>> getBetween(
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(saleService.getBetween(startDate, endDate));
    }

    // 2) Doctor wise list
    @GetMapping("/doctor")
    public ResponseEntity<List<Sale>> getByDoctor(
            @RequestParam("name") String doctorName) {
        return ResponseEntity.ok(saleService.getByDoctorName(doctorName));
    }

    // 2) Doctor wise total
    @GetMapping("/doctor/total")
    public ResponseEntity<Double> getDoctorTotal(
            @RequestParam("name") String doctorName) {
        return ResponseEntity.ok(saleService.getTotalByDoctor(doctorName));
    }

    // 3) Payment method wise list
    @GetMapping("/payment")
    public ResponseEntity<List<Sale>> getByPaymentMethod(
            @RequestParam("method") String paymentMethod) {
        return ResponseEntity.ok(saleService.getByPaymentMethod(paymentMethod));
    }

    // 3) Payment method wise total
    @GetMapping("/payment/total")
    public ResponseEntity<Double> getPaymentTotal(
            @RequestParam("method") String paymentMethod) {
        return ResponseEntity.ok(saleService.getTotalByPaymentMethod(paymentMethod));
    }
}
