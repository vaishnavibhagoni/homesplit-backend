package com.homesplit.homesplit.repository;

import com.homesplit.homesplit.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
}