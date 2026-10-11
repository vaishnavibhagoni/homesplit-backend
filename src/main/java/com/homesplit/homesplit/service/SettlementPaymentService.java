package com.homesplit.homesplit.service;

import com.homesplit.homesplit.model.SettlementPayment;
import com.homesplit.homesplit.repository.SettlementPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SettlementPaymentService {

    private final SettlementPaymentRepository repository;

    public SettlementPaymentService(
            SettlementPaymentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SettlementPayment recordPayment(SettlementPayment payment) {
        if (payment.getHouseholdId() == null
                || payment.getFromUserId() == null
                || payment.getToUserId() == null
                || payment.getFromUserId().equals(payment.getToUserId())
                || payment.getAmount() == null
                || !Double.isFinite(payment.getAmount())
                || payment.getAmount() <= 0) {
            throw new IllegalArgumentException(
                    "Enter valid payer, receiver, and payment amount."
            );
        }

        // The payment date is assigned automatically if not supplied.
        return repository.save(payment);
    }

    public List<SettlementPayment> getPaymentHistory(Long householdId) {
        return repository.findByHouseholdIdOrderByPaidAtDesc(householdId);
    }
}
