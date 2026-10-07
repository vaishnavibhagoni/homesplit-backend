package com.homesplit.homesplit.controller;

import com.homesplit.homesplit.model.User;
import com.homesplit.homesplit.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        try {
            User loggedInUser = userService.loginUser(
                    user.getEmail(),
                    user.getPassword()
            );

            return "Login successful! Welcome " + loggedInUser.getName();

        } catch (Exception e) {
            e.printStackTrace();
            return "LOGIN ERROR: " + e.getMessage();
        }
    }
}