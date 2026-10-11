package com.homesplit.homesplit.service;

import com.homesplit.homesplit.model.ExpenseSplit;
import com.homesplit.homesplit.repository.ExpenseSplitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseSplitService {

    private final ExpenseSplitRepository expenseSplitRepository;

    public ExpenseSplitService(
            ExpenseSplitRepository expenseSplitRepository) {
        this.expenseSplitRepository = expenseSplitRepository;
    }

    public ExpenseSplit addSplit(ExpenseSplit split) {
        return expenseSplitRepository.save(split);
    }

    public List<ExpenseSplit> getSplitsByExpenseId(Long expenseId) {
        return expenseSplitRepository.findByExpenseId(expenseId);
    }

    public List<ExpenseSplit> calculateEqualSplit(
            Long expenseId,
            Double totalAmount,
            List<Long> userIds) {

        if (expenseId == null || totalAmount == null
                || !Double.isFinite(totalAmount)
                || totalAmount < 0
                || userIds == null || userIds.isEmpty()
                || userIds.contains(null)) {
            throw new IllegalArgumentException(
                    "Valid expense details and at least one member are required."
            );
        }

        if (userIds.stream().distinct().count() != userIds.size()) {
            throw new IllegalArgumentException(
                    "Each member can appear only once in an expense split."
            );
        }

        long totalPaise = Math.round(totalAmount * 100);
        long basePaise = totalPaise / userIds.size();
        long remainderPaise = totalPaise % userIds.size();

        // Replace existing splits for this expense.
        expenseSplitRepository.deleteByExpenseId(expenseId);

        List<ExpenseSplit> splits = new ArrayList<>();

        for (int i = 0; i < userIds.size(); i++) {
            long memberPaise =
                    basePaise + (i < remainderPaise ? 1 : 0);

            ExpenseSplit split = new ExpenseSplit(
                    expenseId,
                    userIds.get(i),
                    memberPaise / 100.0
            );

            splits.add(expenseSplitRepository.save(split));
        }

        return splits;
    }
}