package com.homesplit.homesplit.controller;

import com.homesplit.homesplit.model.Expense;
import com.homesplit.homesplit.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    @GetMapping("/household/{householdId}")
    public List<Expense> getExpensesByHousehold(
            @PathVariable Long householdId) {
        return expenseService.getExpensesByHousehold(householdId);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
    }
}