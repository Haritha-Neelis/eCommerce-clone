package com.ecommerce.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusChangedEvent {
    private UUID orderId;
    private UUID userId;
    private String previousStatus;
    private String newStatus;
    private LocalDateTime occurredAt;
}
