package com.homesplit.homesplit.controller;

import com.homesplit.homesplit.model.HouseholdMember;
import com.homesplit.homesplit.service.HouseholdMemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/household-members")
public class HouseholdMemberController {

    private final HouseholdMemberService householdMemberService;

    public HouseholdMemberController(
            HouseholdMemberService householdMemberService) {
        this.householdMemberService = householdMemberService;
    }

    @PostMapping
    public HouseholdMember addMember(
            @RequestBody HouseholdMember member) {

        return householdMemberService.addMember(member);
    }
}