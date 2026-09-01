package com.ecommerce.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFailedEvent {
    private UUID paymentId;
    private UUID orderId;
    private UUID userId;
    private String reason;
    private String status;
    private LocalDateTime occurredAt;
}
