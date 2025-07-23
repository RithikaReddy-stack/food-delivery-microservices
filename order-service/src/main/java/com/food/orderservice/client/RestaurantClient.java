package com.food.orderservice.client;

import com.food.orderservice.dto.RestaurantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service")
public interface RestaurantClient {
    
    @GetMapping("/api/restaurants/public/{id}/menu")
    RestaurantDTO getRestaurantMenu(@PathVariable("id") Long restaurantId);
}
