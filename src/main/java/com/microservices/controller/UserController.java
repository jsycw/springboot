package com.microservices.controller;

import com.microservices.model.dto.AuthDto;
import com.microservices.model.dto.LoginDto;
import com.microservices.model.dto.Message;
import com.microservices.model.entity.User;
import com.microservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginRequest) {
        try {
            AuthDto authResponse = userService.login(loginRequest);
            return ResponseEntity.ok(authResponse);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Message("Invalid username or password"));
        }
    }

    @GetMapping("/users/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = extractToken(authorizationHeader);
            User userProfile = userService.getUserProfile(token);
            return ResponseEntity.ok(userProfile);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
        }
    }

    @PutMapping("/users/profile/update")
    public ResponseEntity<?> updateUserProfile(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody User updateRequest) {
        try {
            String token = extractToken(authorizationHeader);
            User updatedUser = userService.updateUserProfile(token, updateRequest);

            updatedUser.setPassword(null);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception exception) {
            return ResponseEntity.badRequest()
                    .body(new Message(exception.getMessage()));
        }
    }

    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new RuntimeException("Authorization token is missing or invalid");
    }
}