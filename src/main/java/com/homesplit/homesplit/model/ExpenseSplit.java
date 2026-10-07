package com.homesplit.homesplit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExpenseSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long expenseId;

    private Long userId;

    private Double shareAmount;

    public ExpenseSplit() {
    }

    public ExpenseSplit(Long expenseId, Long userId, Double shareAmount) {
        this.expenseId = expenseId;
        this.userId = userId;
        this.shareAmount = shareAmount;
    }

    public Long getId() {
        return id;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(Double shareAmount) {
        this.shareAmount = shareAmount;
    }
}