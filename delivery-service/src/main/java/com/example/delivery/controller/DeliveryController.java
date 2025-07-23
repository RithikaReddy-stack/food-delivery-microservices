package com.example.delivery.controller;

import com.example.delivery.model.Delivery;
import com.example.delivery.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    @GetMapping("/{orderId}")
    public Delivery getDelivery(@PathVariable Long orderId) {
        return service.getByOrderId(orderId);
    }
}
