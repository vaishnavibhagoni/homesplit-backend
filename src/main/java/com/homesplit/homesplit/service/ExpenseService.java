package com.homesplit.homesplit.service;

import com.homesplit.homesplit.model.Expense;
import com.homesplit.homesplit.repository.ExpenseRepository;
import com.homesplit.homesplit.repository.ExpenseSplitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository expenseSplitRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            ExpenseSplitRepository expenseSplitRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseSplitRepository = expenseSplitRepository;
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByHousehold(Long householdId) {
        return expenseRepository.findByHouseholdId(householdId);
    }

    @Transactional
    public void deleteExpense(Long expenseId) {
        if (!expenseRepository.existsById(expenseId)) {
            throw new IllegalArgumentException(
                    "Expense not found: " + expenseId
            );
        }

        // Delete split records first, then the expense.
        expenseSplitRepository.deleteByExpenseId(expenseId);
        expenseRepository.deleteById(expenseId);
    }
}