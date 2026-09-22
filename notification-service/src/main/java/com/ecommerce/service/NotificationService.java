package com.ecommerce.service;

import com.ecommerce.event.OrderConfirmedEvent;
import com.ecommerce.event.OrderCreatedEvent;
import com.ecommerce.event.OrderStatusChangedEvent;
import com.ecommerce.event.PaymentCompletedEvent;
import com.ecommerce.event.PaymentFailedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class NotificationService {

    private final List<Map<String, Object>> recentNotifications = new ArrayList<>();

    public List<Map<String, Object>> getRecentNotifications() {
        synchronized (recentNotifications) {
            return new ArrayList<>(recentNotifications);
        }
    }

    @KafkaListener(topics = "order-created", groupId = "notification-service-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        sendNotification("Order Created", "Order " + event.getOrderId() + " has been created and payment is pending.");
    }

    @KafkaListener(topics = "payment-completed", groupId = "notification-service-group")
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        sendNotification("Payment Completed", "Payment for order " + event.getOrderId() + " succeeded.");
    }

    @KafkaListener(topics = "payment-failed", groupId = "notification-service-group")
    public void handlePaymentFailed(PaymentFailedEvent event) {
        sendNotification("Payment Failed", "Payment for order " + event.getOrderId() + " failed: " + event.getReason());
    }

    @KafkaListener(topics = "order-confirmed", groupId = "notification-service-group")
    public void handleOrderConfirmed(OrderConfirmedEvent event) {
        sendNotification("Order Confirmed", "Order " + event.getOrderId() + " has been confirmed.");
    }

    @KafkaListener(topics = "order-status-changed", groupId = "notification-service-group")
    public void handleOrderStatusChanged(OrderStatusChangedEvent event) {
        sendNotification("Order Updated", "Order " + event.getOrderId() + " status changed from " + event.getPreviousStatus() + " to " + event.getNewStatus());
    }

    private void sendNotification(String title, String message) {
        Map<String, Object> notification = new ConcurrentHashMap<>();
        notification.put("title", title);
        notification.put("message", message);
        notification.put("status", "SENT");

        synchronized (recentNotifications) {
            recentNotifications.add(notification);
            if (recentNotifications.size() > 25) {
                recentNotifications.remove(0);
            }
        }

        log.info("Notification sent: {} - {}", title, message);
    }
}
