package com.homesplit.homesplit.repository;

import com.homesplit.homesplit.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByHouseholdId(Long householdId);
}