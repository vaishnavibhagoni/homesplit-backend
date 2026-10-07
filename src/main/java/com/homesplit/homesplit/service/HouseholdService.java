package com.homesplit.homesplit.service;

import com.homesplit.homesplit.model.Household;
import com.homesplit.homesplit.repository.HouseholdRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }
}