package com.homesplit.homesplit.repository;

import com.homesplit.homesplit.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
