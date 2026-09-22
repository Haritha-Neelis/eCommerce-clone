package com.ecommerce.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NotificationServiceTest {

    @Test
    void shouldExposeRecentNotificationsWithoutThrowing() {
        NotificationService service = new NotificationService();
        assertDoesNotThrow(service::getRecentNotifications);
    }
}
