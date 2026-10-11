package com.homesplit.homesplit.controller;

import com.homesplit.homesplit.model.ExpenseSplit;
import com.homesplit.homesplit.service.ExpenseSplitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense-splits")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ExpenseSplitController {

    private final ExpenseSplitService expenseSplitService;

    public ExpenseSplitController(
            ExpenseSplitService expenseSplitService) {
        this.expenseSplitService = expenseSplitService;
    }

    @PostMapping
    public ExpenseSplit addSplit(@RequestBody ExpenseSplit split) {
        return expenseSplitService.addSplit(split);
    }

    @GetMapping("/expense/{expenseId}")
    public List<ExpenseSplit> getSplitsByExpenseId(
            @PathVariable Long expenseId) {
        return expenseSplitService.getSplitsByExpenseId(expenseId);
    }

    @PostMapping("/equal")
    public List<ExpenseSplit> calculateEqualSplit(
            @RequestParam Long expenseId,
            @RequestParam Double totalAmount,
            @RequestBody List<Long> userIds) {

        return expenseSplitService.calculateEqualSplit(
                expenseId, totalAmount, userIds);
    }
}