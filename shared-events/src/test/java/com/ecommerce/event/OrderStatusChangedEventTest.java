package com.ecommerce.event;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatusChangedEventTest {

    @Test
    void shouldCreateEventWithAllFields() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String previousStatus = "PENDING";
        String newStatus = "SHIPPED";
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 14, 20, 5);

        OrderStatusChangedEvent event = new OrderStatusChangedEvent(orderId, userId, previousStatus, newStatus, occurredAt);

        assertAll(
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals(previousStatus, event.getPreviousStatus()),
                () -> assertEquals(newStatus, event.getNewStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldSetAndGetValuesUsingLombokAccessors() {
        OrderStatusChangedEvent event = new OrderStatusChangedEvent();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 15, 43, 10);

        event.setOrderId(orderId);
        event.setUserId(userId);
        event.setPreviousStatus("CREATED");
        event.setNewStatus("DELIVERED");
        event.setOccurredAt(occurredAt);

        assertAll(
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals("CREATED", event.getPreviousStatus()),
                () -> assertEquals("DELIVERED", event.getNewStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldHaveEqualsAndHashCodeBasedOnAllFields() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 17, 10, 0);

        OrderStatusChangedEvent first = new OrderStatusChangedEvent(orderId, userId, "PENDING", "SHIPPED", occurredAt);
        OrderStatusChangedEvent second = new OrderStatusChangedEvent(orderId, userId, "PENDING", "SHIPPED", occurredAt);
        OrderStatusChangedEvent different = new OrderStatusChangedEvent(UUID.randomUUID(), userId, "PENDING", "SHIPPED", occurredAt);

        assertAll(
                () -> assertEquals(first, second),
                () -> assertEquals(first.hashCode(), second.hashCode()),
                () -> assertNotEquals(first, different),
                () -> assertNotEquals(first, null)
        );
    }

    @Test
    void shouldHaveUsefulToString() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 9, 15, 0);

        OrderStatusChangedEvent event = new OrderStatusChangedEvent(orderId, userId, "QUEUED", "PROCESSING", occurredAt);
        String text = event.toString();

        assertAll(
                () -> assertTrue(text.contains("OrderStatusChangedEvent")),
                () -> assertTrue(text.contains(orderId.toString())),
                () -> assertTrue(text.contains(userId.toString())),
                () -> assertTrue(text.contains("QUEUED")),
                () -> assertTrue(text.contains("PROCESSING"))
        );
    }
}
