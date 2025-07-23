package com.example.delivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String status;
    private String deliveryAgent;

    public Delivery() {}

    public Delivery(Long orderId, String status, String deliveryAgent) {
        this.orderId = orderId;
        this.status = status;
        this.deliveryAgent = deliveryAgent;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDeliveryAgent() { return deliveryAgent; }
    public void setDeliveryAgent(String deliveryAgent) { this.deliveryAgent = deliveryAgent; }
}
