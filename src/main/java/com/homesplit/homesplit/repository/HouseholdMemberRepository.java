package com.homesplit.homesplit.repository;

import com.homesplit.homesplit.model.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
}