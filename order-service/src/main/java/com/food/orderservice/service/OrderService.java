package com.food.orderservice.service;

import com.food.orderservice.client.RestaurantClient;
import com.food.orderservice.client.UserClient;
import com.food.orderservice.dto.RestaurantDTO;
import com.food.orderservice.model.Order;
import com.food.orderservice.model.OrderItem;
import com.food.orderservice.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import com.food.orderservice.kafka.KafkaProducer;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepo;
    private final UserClient userClient;
    private final RestaurantClient restaurantClient;
    private final KafkaProducer kafkaProducer;


    public Order placeOrder(String email, Long restaurantId, List<OrderItem> items) {
        double total = items.stream().mapToDouble(OrderItem::getPrice).sum();

        Order order = Order.builder()
                .customerEmail(email)
                .restaurantId(restaurantId)
                .total(total)
                .build();

        items.forEach(item -> item.setOrder(order));
        order.setItems(items);
        
        Order savedOrder = orderRepo.save(order);

        // Send Kafka message
        kafkaProducer.sendOrderEvent("Order ID " + savedOrder.getId() + " placed by " + email);

        return orderRepo.save(order);

    }

    public void createOrder(String userEmail, Long restaurantId) {
        String userName = getUserNameWithFallback(userEmail);
        RestaurantDTO restaurant = getRestaurantMenuWithFallback(restaurantId);

        System.out.println("Placing order for " + userName + " at restaurant: " + restaurant.getName());
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "userFallback")
    public String getUserNameWithFallback(String email) {
        return userClient.getNameByEmail(email);
    }

    public String userFallback(String email, Throwable t) {
        log.error("User service fallback triggered: {}", t.getMessage());
        return "Guest User";
    }

    @CircuitBreaker(name = "restaurantService", fallbackMethod = "restaurantFallback")
    public RestaurantDTO getRestaurantMenuWithFallback(Long restaurantId) {
        return restaurantClient.getRestaurantMenu(restaurantId);
    }

    public RestaurantDTO restaurantFallback(Long restaurantId, Throwable t) {
        log.error("Restaurant service fallback triggered: {}", t.getMessage());
        return new RestaurantDTO();
    }

    public List<Order> getOrdersByCustomer(String email) {
        return orderRepo.findByCustomerEmail(email);
    }
    
    
}
