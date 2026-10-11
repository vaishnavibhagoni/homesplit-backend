package com.homesplit.homesplit.repository;

import com.homesplit.homesplit.model.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExpenseSplitRepository
        extends JpaRepository<ExpenseSplit, Long> {

    List<ExpenseSplit> findByExpenseId(Long expenseId);

    @Transactional
    void deleteByExpenseId(Long expenseId);
}