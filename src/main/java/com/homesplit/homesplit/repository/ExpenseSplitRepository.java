package com.homesplit.homesplit.repository;

import com.homesplit.homesplit.model.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long> {
}