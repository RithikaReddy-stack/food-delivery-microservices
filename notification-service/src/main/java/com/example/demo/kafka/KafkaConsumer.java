package com.example.demo.kafka;

import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    public KafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void consumeOrderEvent(String message) {
        try {
            Notification notification = objectMapper.readValue(message, Notification.class);
            notification.setMessage("Order placed and saved to notifications.");
            notificationService.save(notification);
            System.out.println("✅ Saved notification for user: " + notification.getUserId());
        } catch (Exception e) {
            System.err.println("❌ Failed to process message: " + e.getMessage());
        }
    }
}
