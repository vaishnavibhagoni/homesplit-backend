package com.homesplit.homesplit.controller;
import com.homesplit.homesplit.dto.AddMemberRequest;
import com.homesplit.homesplit.dto.UserResponse;
import com.homesplit.homesplit.model.HouseholdMember;
import com.homesplit.homesplit.service.HouseholdMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
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

    @GetMapping("/household/{householdId}")
    public List<HouseholdMember> getMembersByHousehold(
            @PathVariable Long householdId) {

        return householdMemberService.getMembersByHousehold(householdId);
    }

    @GetMapping("/household/{householdId}/users")
    public List<UserResponse> getUsersByHousehold(
            @PathVariable Long householdId) {

        return householdMemberService.getUsersByHousehold(householdId);
    }
    @GetMapping("/user/{userId}")
public List<HouseholdMember> getHouseholdsByUser(
        @PathVariable Long userId) {

    return householdMemberService.getHouseholdsByUser(userId);
}
    @PostMapping("/add")
public UserResponse addMemberToHousehold(
        @RequestBody AddMemberRequest request) {
    try {
        System.out.println("ADD MEMBER REQUEST RECEIVED");
        System.out.println("Household ID: " + request.getHouseholdId());
        System.out.println("Name: " + request.getName());
        System.out.println("Email: " + request.getEmail());

        return householdMemberService.addMemberToHousehold(request);

    } catch (Exception e) {
        System.err.println("ADD MEMBER FAILED:");
        e.printStackTrace();
        throw e;
    }
 
}
    @DeleteMapping("/household/{householdId}/user/{userId}")
public void removeMember(
        @PathVariable Long householdId,
        @PathVariable Long userId) {

    householdMemberService.removeMemberFromHousehold(
            householdId, userId);
}
}