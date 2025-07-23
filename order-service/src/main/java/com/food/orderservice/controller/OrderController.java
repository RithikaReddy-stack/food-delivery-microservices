package com.food.orderservice.controller;

import com.food.orderservice.dto.OrderRequest;
import com.food.orderservice.model.Order;
import com.food.orderservice.security.JwtTokenUtil;
import com.food.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/place")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest request,
                                            @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String email = jwtTokenUtil.getEmail(token);
        Order saved = orderService.placeOrder(email, request.getRestaurantId(), request.getItems());
        return ResponseEntity.ok(saved);
    }
    
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestParam String email,
                                              @RequestParam Long restaurantId) {
        orderService.createOrder(email, restaurantId);
        return ResponseEntity.ok("Order placed successfully.");
    }

    @GetMapping("/my-orders")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Order>> getMyOrders(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String email = jwtTokenUtil.getEmail(token);
        return ResponseEntity.ok(orderService.getOrdersByCustomer(email));
    }
}
