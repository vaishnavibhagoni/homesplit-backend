
package com.homesplit.homesplit.service;

import com.homesplit.homesplit.model.Household;
import com.homesplit.homesplit.repository.HouseholdRepository;
import com.homesplit.homesplit.repository.HouseholdMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HouseholdService {

    private final HouseholdRepository householdRepository;
    private final HouseholdMemberRepository householdMemberRepository;

    public HouseholdService(
            HouseholdRepository householdRepository,
            HouseholdMemberRepository householdMemberRepository) {
        this.householdRepository = householdRepository;
        this.householdMemberRepository = householdMemberRepository;
    }

    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    public List<Household> getHouseholdsByUser(Long userId) {
        return householdMemberRepository.findByUserId(userId)
                .stream()
                .map(member -> householdRepository
                        .findById(member.getHouseholdId())
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }
}
