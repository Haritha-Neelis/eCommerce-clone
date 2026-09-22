package com.ecommerce.controller;

import com.ecommerce.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "service", "notification-service"));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getNotifications() {
        return ResponseEntity.ok(Map.of(
                "message", "Notifications endpoint is active",
                "notifications", notificationService.getRecentNotifications()
        ));
    }
}
