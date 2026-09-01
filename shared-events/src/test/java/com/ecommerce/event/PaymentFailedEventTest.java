package com.ecommerce.event;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFailedEventTest {

    @Test
    void shouldCreateEventWithAllFields() {
        UUID paymentId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String reason = "INSUFFICIENT_FUNDS";
        String status = "FAILED";
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 7, 30, 20);

        PaymentFailedEvent event = new PaymentFailedEvent(paymentId, orderId, userId, reason, status, occurredAt);

        assertAll(
                () -> assertEquals(paymentId, event.getPaymentId()),
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals(reason, event.getReason()),
                () -> assertEquals(status, event.getStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldSetAndGetValuesUsingLombokAccessors() {
        PaymentFailedEvent event = new PaymentFailedEvent();
        UUID paymentId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 21, 5, 0);

        event.setPaymentId(paymentId);
        event.setOrderId(orderId);
        event.setUserId(userId);
        event.setReason("CARD_EXPIRED");
        event.setStatus("REJECTED");
        event.setOccurredAt(occurredAt);

        assertAll(
                () -> assertEquals(paymentId, event.getPaymentId()),
                () -> assertEquals(orderId, event.getOrderId()),
                () -> assertEquals(userId, event.getUserId()),
                () -> assertEquals("CARD_EXPIRED", event.getReason()),
                () -> assertEquals("REJECTED", event.getStatus()),
                () -> assertEquals(occurredAt, event.getOccurredAt())
        );
    }

    @Test
    void shouldHaveEqualsAndHashCodeBasedOnAllFields() {
        UUID paymentId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 22, 40, 10);

        PaymentFailedEvent first = new PaymentFailedEvent(paymentId, orderId, userId, "INSUFFICIENT_FUNDS", "FAILED", occurredAt);
        PaymentFailedEvent second = new PaymentFailedEvent(paymentId, orderId, userId, "INSUFFICIENT_FUNDS", "FAILED", occurredAt);
        PaymentFailedEvent different = new PaymentFailedEvent(UUID.randomUUID(), orderId, userId, "INSUFFICIENT_FUNDS", "FAILED", occurredAt);

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
        LocalDateTime occurredAt = LocalDateTime.of(2026, 9, 1, 23, 12, 0);

        PaymentFailedEvent event = new PaymentFailedEvent(paymentId, orderId, userId, "DECLINED", "FAILED", occurredAt);
        String text = event.toString();

        assertAll(
                () -> assertTrue(text.contains("PaymentFailedEvent")),
                () -> assertTrue(text.contains(paymentId.toString())),
                () -> assertTrue(text.contains(orderId.toString())),
                () -> assertTrue(text.contains(userId.toString())),
                () -> assertTrue(text.contains("DECLINED")),
                () -> assertTrue(text.contains("FAILED"))
        );
    }
}
