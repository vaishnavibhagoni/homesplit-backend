package com.homesplit.homesplit.service;

import com.homesplit.homesplit.model.ExpenseSplit;
import com.homesplit.homesplit.repository.ExpenseSplitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseSplitService {

    private final ExpenseSplitRepository expenseSplitRepository;

    public ExpenseSplitService(ExpenseSplitRepository expenseSplitRepository) {
        this.expenseSplitRepository = expenseSplitRepository;
    }

    public ExpenseSplit addSplit(ExpenseSplit split) {
        return expenseSplitRepository.save(split);
    }

    public List<ExpenseSplit> calculateEqualSplit(
            Long expenseId,
            Double totalAmount,
            List<Long> userIds) {

        List<ExpenseSplit> splits = new ArrayList<>();

        double shareAmount = totalAmount / userIds.size();

        for (Long userId : userIds) {

            ExpenseSplit split = new ExpenseSplit(
                    expenseId,
                    userId,
                    shareAmount
            );

            splits.add(
                    expenseSplitRepository.save(split)
            );
        }

        return splits;
    }
}