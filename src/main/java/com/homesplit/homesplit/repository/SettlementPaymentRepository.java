package com.homesplit.homesplit.repository;

import com.homesplit.homesplit.model.SettlementPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementPaymentRepository
        extends JpaRepository<SettlementPayment, Long> {

    List<SettlementPayment> findByHouseholdIdOrderByPaidAtDesc(
            Long householdId
    );
}
