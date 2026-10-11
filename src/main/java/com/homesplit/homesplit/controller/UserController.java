
package com.homesplit.homesplit.controller;

import com.homesplit.homesplit.model.User;
import com.homesplit.homesplit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User loggedInUser = userService.loginUser(
                    user.getEmail(),
                    user.getPassword()
            );

            return ResponseEntity.ok(Map.of(
                    "message", "Login successful!",
                    "userId", loggedInUser.getId(),
                    "name", loggedInUser.getName(),
                    "email", loggedInUser.getEmail()
            ));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
