package com.example.delivery.kafka;

import com.example.delivery.service.DeliveryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private final DeliveryService deliveryService;

    public OrderEventConsumer(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @KafkaListener(topics = "order-events", groupId = "delivery-group")
    public void consume(String message) {
        try {
            System.out.println("📦 Delivery Service received: " + message);

            // Simple parsing — extract numeric orderId
            Long orderId = Long.parseLong(message.replaceAll("\\D+", ""));
            deliveryService.assignDelivery(orderId);

            System.out.println("✅ Delivery assigned for orderId: " + orderId);
        } catch (Exception e) {
            System.err.println("❌ Error handling Kafka message: " + e.getMessage());
        }
    }
}
