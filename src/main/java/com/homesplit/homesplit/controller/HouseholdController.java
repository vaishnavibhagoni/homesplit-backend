package com.homesplit.homesplit.controller;

import com.homesplit.homesplit.model.Household;
import com.homesplit.homesplit.service.HouseholdService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public Household createHousehold(@RequestBody Household household) {
        return householdService.createHousehold(household);
    }
}