package com.homesplit.homesplit.service;

import com.homesplit.homesplit.model.Expense;
import com.homesplit.homesplit.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}