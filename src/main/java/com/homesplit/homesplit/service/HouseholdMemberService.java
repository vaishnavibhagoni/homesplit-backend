package com.homesplit.homesplit.service;

import com.homesplit.homesplit.model.HouseholdMember;
import com.homesplit.homesplit.repository.HouseholdMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseholdMemberService {

    private final HouseholdMemberRepository householdMemberRepository;

    public HouseholdMemberService(
            HouseholdMemberRepository householdMemberRepository) {
        this.householdMemberRepository = householdMemberRepository;
    }

    public HouseholdMember addMember(HouseholdMember member) {
        return householdMemberRepository.save(member);
    }
}