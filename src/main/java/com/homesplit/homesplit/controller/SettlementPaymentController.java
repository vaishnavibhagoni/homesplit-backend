
package com.homesplit.homesplit.controller;

import com.homesplit.homesplit.model.SettlementPayment;
import com.homesplit.homesplit.service.SettlementPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlement-payments")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class SettlementPaymentController {

    private final SettlementPaymentService service;

    public SettlementPaymentController(
            SettlementPaymentService service) {
        this.service = service;
    }

    @PostMapping
    public SettlementPayment recordPayment(
            @RequestBody SettlementPayment payment) {
        return service.recordPayment(payment);
    }

    @GetMapping("/household/{householdId}")
    public List<SettlementPayment> getPaymentHistory(
            @PathVariable Long householdId) {
        return service.getPaymentHistory(householdId);
    }
}

