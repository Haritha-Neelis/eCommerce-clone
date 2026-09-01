package com.ecommerce.event;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCompletedEventTest {

    @Test
    void shouldCreateEventWithAllFields() {
        UUID paymentId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BigDecimal amount = new BigDecimal("99.00");
        String paymentMethod = "CARD";
        String status = "COMPLETED";
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 9, 10, 0);

        PaymentCompletedEvent event = new PaymentCompletedEvent(paymentId, orderId, userId, amount, paymentMethod, status, occurredAt);

        assertAll(
                () -> assertEquals(paymentId, event.getPaymentId()),
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals(amount, event.getAmount()),
                () -> assertEquals(paymentMethod, event.getPaymentMethod()),
                () -> assertEquals(status, event.getStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldSetAndGetValuesUsingLombokAccessors() {
        PaymentCompletedEvent event = new PaymentCompletedEvent();
        UUID paymentId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BigDecimal amount = new BigDecimal("125.75");
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 18, 45, 0);

        event.setPaymentId(paymentId);
        event.setOrderId(orderId);
        event.setUserId(userId);
        event.setAmount(amount);
        event.setPaymentMethod("BANK_TRANSFER");
        event.setStatus("APPROVED");
        event.setOccurredAt(occurredAt);

        assertAll(
                () -> assertEquals(paymentId, event.getPaymentId()),
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals(amount, event.getAmount()),
                () -> assertEquals("BANK_TRANSFER", event.getPaymentMethod()),
                () -> assertEquals("APPROVED", event.getStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldHaveEqualsAndHashCodeBasedOnAllFields() {
        UUID paymentId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BigDecimal amount = new BigDecimal("230.20");
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 20, 0, 0);

        PaymentCompletedEvent first = new PaymentCompletedEvent(paymentId, orderId, userId, amount, "CARD", "COMPLETED", occurredAt);
        PaymentCompletedEvent second = new PaymentCompletedEvent(paymentId, orderId, userId, amount, "CARD", "COMPLETED", occurredAt);
        PaymentCompletedEvent different = new PaymentCompletedEvent(UUID.randomUUID(), orderId, userId, amount, "CARD", "COMPLETED", occurredAt);

        assertAll(
                () -> assertEquals(first, second),
                () -> assertEquals(first.hashCode(), second.hashCode()),
                () -> assertNotEquals(first, different),
                () -> assertNotEquals(first, null)
        );
    }

    @Test
    void shouldHaveUsefulToString() {
        UUID paymentId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BigDecimal amount = new BigDecimal("400.00");
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 13, 15, 0);

        PaymentCompletedEvent event = new PaymentCompletedEvent(paymentId, orderId, userId, amount, "PAYPAL", "COMPLETED", occurredAt);
        String text = event.toString();

        assertAll(
                () -> assertTrue(text.contains("PaymentCompletedEvent")),
                () -> assertTrue(text.contains(paymentId.toString())),
                () -> assertTrue(text.contains(orderId.toString())),
                () -> assertTrue(text.contains(userId.toString())),
                () -> assertTrue(text.contains("PAYPAL")),
                () -> assertTrue(text.contains("COMPLETED"))
        );
    }
}
