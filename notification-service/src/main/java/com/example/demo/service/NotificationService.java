package com.example.demo.service;

import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    public Iterable<Notification> getAll() {
        return repository.findAll();
    }
}