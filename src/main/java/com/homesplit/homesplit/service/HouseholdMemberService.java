
package com.homesplit.homesplit.service;

import com.homesplit.homesplit.dto.AddMemberRequest;
import com.homesplit.homesplit.dto.UserResponse;
import com.homesplit.homesplit.model.HouseholdMember;
import com.homesplit.homesplit.model.User;
import com.homesplit.homesplit.repository.HouseholdMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseholdMemberService {

    private final HouseholdMemberRepository householdMemberRepository;
    private final UserService userService;

    public HouseholdMemberService(
            HouseholdMemberRepository householdMemberRepository,
            UserService userService) {
        this.householdMemberRepository = householdMemberRepository;
        this.userService = userService;
    }

    public HouseholdMember addMember(HouseholdMember member) {
        boolean alreadyExists = householdMemberRepository
                .findByHouseholdIdAndUserId(
                        member.getHouseholdId(),
                        member.getUserId()
                ).isPresent();

        if (alreadyExists) {
            throw new IllegalArgumentException(
                    "This user is already a member of the household."
            );
        }

        return householdMemberRepository.save(member);
    }

    public List<HouseholdMember> getMembersByHousehold(Long householdId) {
        return householdMemberRepository.findByHouseholdId(householdId);
    }

    public List<HouseholdMember> getHouseholdsByUser(Long userId) {
        return householdMemberRepository.findByUserId(userId);
    }

    public List<UserResponse> getUsersByHousehold(Long householdId) {
        List<HouseholdMember> members =
                householdMemberRepository.findByHouseholdId(householdId);

        List<UserResponse> users = new ArrayList<>();

        for (HouseholdMember member : members) {
            User user = userService.getUserById(member.getUserId());

            users.add(new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail()
            ));
        }

        return users;
    }

    @Transactional
    public void removeMemberFromHousehold(Long householdId, Long userId) {
        HouseholdMember member = householdMemberRepository
                .findByHouseholdIdAndUserId(householdId, userId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Member not found in this household"
                        ));

        householdMemberRepository.delete(member);
    }

    @Transactional
    public UserResponse addMemberToHousehold(AddMemberRequest request) {
        User user;

        try {
            user = userService.getUserByEmail(request.getEmail());
        } catch (RuntimeException e) {
            user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());

            // Retained from your existing implementation.
            // Replace with a secure account-invitation flow before deployment.
            user.setPassword("homesplit123");

            user = userService.registerUser(user);
        }

        boolean alreadyExists = householdMemberRepository
                .findByHouseholdIdAndUserId(
                        request.getHouseholdId(),
                        user.getId()
                ).isPresent();

        if (alreadyExists) {
            throw new IllegalArgumentException(
                    "This user is already a member of the household."
            );
        }

        householdMemberRepository.save(
                new HouseholdMember(
                        request.getHouseholdId(),
                        user.getId()
                )
        );

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
