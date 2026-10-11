package com.homesplit.homesplit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long householdId;
    private Long paidByUserId;
    private String description;
    private Double amount;

    private LocalDateTime createdAt;

    public Expense() {
    }

    public Expense(
            Long householdId,
            Long paidByUserId,
            String description,
            Double amount) {
        this.householdId = householdId;
        this.paidByUserId = paidByUserId;
        this.description = description;
        this.amount = amount;
    }

        @PrePersist
    public void setCreatedAtIfMissing() {
        System.out.println("PrePersist called for expense: " + description);

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        System.out.println("Expense createdAt: " + createdAt);
    }

    public Long getId() {
        return id;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public Long getPaidByUserId() {
        return paidByUserId;
    }

    public void setPaidByUserId(Long paidByUserId) {
        this.paidByUserId = paidByUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
