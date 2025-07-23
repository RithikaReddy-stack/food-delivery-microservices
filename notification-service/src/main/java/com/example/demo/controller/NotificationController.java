package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*") // Allow cross-origin if frontend like Angular is used
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    // GET /api/notifications
    @GetMapping
    public Iterable<Notification> getAllNotifications() {
        return service.getAll();
    }

    // Optional: GET by user ID (future use)
    // GET /api/notifications/user/1
    @GetMapping("/user/{userId}")
    public Iterable<Notification> getByUserId(@PathVariable Long userId) {
        return service.getAll(); // You can implement filter logic in service/repo
    }
}
