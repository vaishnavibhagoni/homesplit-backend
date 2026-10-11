
package com.homesplit.homesplit.repository;

import com.homesplit.homesplit.model.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseholdMemberRepository
        extends JpaRepository<HouseholdMember, Long> {

    List<HouseholdMember> findByHouseholdId(Long householdId);

    Optional<HouseholdMember> findByHouseholdIdAndUserId(
            Long householdId, Long userId);

    List<HouseholdMember> findByUserId(Long userId);
}
