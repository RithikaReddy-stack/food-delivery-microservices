package com.example.delivery.service;

import com.example.delivery.model.Delivery;
import com.example.delivery.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DeliveryService {

    private final DeliveryRepository repository;

    public DeliveryService(DeliveryRepository repository) {
        this.repository = repository;
    }

    public Delivery assignDelivery(Long orderId) {
        String[] agents = {"Ravi", "Sneha", "Arjun", "Priya"};
        String agent = agents[new Random().nextInt(agents.length)];

        Delivery delivery = new Delivery(orderId, "ASSIGNED", agent);
        return repository.save(delivery);
    }

    public Delivery getByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }
}
