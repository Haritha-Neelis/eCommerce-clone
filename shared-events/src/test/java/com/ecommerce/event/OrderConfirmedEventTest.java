package com.ecommerce.event;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderConfirmedEventTest {

    @Test
    void shouldCreateEventWithAllFields() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String status = "CONFIRMED";
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 11, 5, 0);

        OrderConfirmedEvent event = new OrderConfirmedEvent(orderId, userId, status, occurredAt);

        assertAll(
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals(status, event.getStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldSetAndGetValuesUsingLombokAccessors() {
        OrderConfirmedEvent event = new OrderConfirmedEvent();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 12, 30, 45);

        event.setOrderId(orderId);
        event.setUserId(userId);
        event.setStatus("PAID");
        event.setOccurredAt(occurredAt);

        assertAll(
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals("PAID", event.getStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldHaveEqualsAndHashCodeBasedOnAllFields() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 13, 10, 0);

        OrderConfirmedEvent first = new OrderConfirmedEvent(orderId, userId, "CONFIRMED", occurredAt);
        OrderConfirmedEvent second = new OrderConfirmedEvent(orderId, userId, "CONFIRMED", occurredAt);
        OrderConfirmedEvent different = new OrderConfirmedEvent(UUID.randomUUID(), userId, "CONFIRMED", occurredAt);

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
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 19, 0, 0);

        OrderConfirmedEvent event = new OrderConfirmedEvent(orderId, userId, "CONFIRMED", occurredAt);
        String text = event.toString();

        assertAll(
                () -> assertTrue(text.contains("OrderConfirmedEvent")),
                () -> assertTrue(text.contains(orderId.toString())),
                () -> assertTrue(text.contains(userId.toString())),
                () -> assertTrue(text.contains("CONFIRMED"))
        );
    }
}
