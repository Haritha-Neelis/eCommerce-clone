package com.ecommerce.event;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderCreatedEventTest {

    @Test
    void shouldCreateEventWithAllFields() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BigDecimal totalAmount = new BigDecimal("199.99");
        String currency = "USD";
        String status = "CREATED";
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 10, 15, 30);

        OrderCreatedEvent event = new OrderCreatedEvent(orderId, userId, totalAmount, currency, status, occurredAt);

        assertAll(
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals(totalAmount, event.getTotalAmount()),
                () -> assertEquals(currency, event.getCurrency()),
                () -> assertEquals(status, event.getStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldSetAndGetValuesUsingLombokAccessors() {
        OrderCreatedEvent event = new OrderCreatedEvent();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BigDecimal totalAmount = new BigDecimal("123.45");
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 12, 0, 0);

        event.setOrderId(orderId);
        event.setUserId(userId);
        event.setTotalAmount(totalAmount);
        event.setCurrency("EUR");
        event.setStatus("CONFIRMED");
        event.setOccurredAt(occurredAt);

        assertAll(
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals(totalAmount, event.getTotalAmount()),
                () -> assertEquals("EUR", event.getCurrency()),
                () -> assertEquals("CONFIRMED", event.getStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldHaveEqualsAndHashCodeBasedOnAllFields() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BigDecimal totalAmount = new BigDecimal("49.99");
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 8, 45, 0);

        OrderCreatedEvent first = new OrderCreatedEvent(orderId, userId, totalAmount, "USD", "CREATED", occurredAt);
        OrderCreatedEvent second = new OrderCreatedEvent(orderId, userId, totalAmount, "USD", "CREATED", occurredAt);
        OrderCreatedEvent different = new OrderCreatedEvent(UUID.randomUUID(), userId, totalAmount, "USD", "CREATED", occurredAt);

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
        BigDecimal totalAmount = new BigDecimal("25.50");
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 16, 30, 15);

        OrderCreatedEvent event = new OrderCreatedEvent(orderId, userId, totalAmount, "GBP", "PENDING", occurredAt);
        String text = event.toString();

        assertAll(
                () -> assertTrue(text.contains("OrderCreatedEvent")),
                () -> assertTrue(text.contains(orderId.toString())),
                () -> assertTrue(text.contains(userId.toString())),
                () -> assertTrue(text.contains("GBP")),
                () -> assertTrue(text.contains("PENDING"))
        );
    }
}
