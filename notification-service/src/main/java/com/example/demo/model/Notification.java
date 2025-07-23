package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;
    private Long userId;
    private Long restaurantId;
    private String status;
    private String message;

    public Notification() {}

    public Notification(Long userId, Long restaurantId, String status, String message) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.message = message;
    }

    // Getters and Setters
    public String getId() { return id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}