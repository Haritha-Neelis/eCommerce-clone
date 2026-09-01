package com.ecommerce.controller;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
        User user = userService.getUserProfile(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        Long userId = (Long) authentication.getCredentials();
        User user = userService.getUserProfile(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody Map<String, String> updates) {
        String firstName = updates.getOrDefault("firstName", "");
        String lastName = updates.getOrDefault("lastName", "");
        User user = userService.updateUserProfile(userId, firstName, lastName);
        return ResponseEntity.ok(user);
    }
}
