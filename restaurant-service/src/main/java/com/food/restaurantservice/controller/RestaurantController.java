package com.food.restaurantservice.controller;

import com.food.restaurantservice.model.MenuItem;
import com.food.restaurantservice.model.Restaurant;
import com.food.restaurantservice.security.JwtTokenUtil;
import com.food.restaurantservice.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final JwtTokenUtil jwtUtil;

    // ✅ Add restaurant with ownerEmail from JWT
    @PostMapping("/add")
    @PreAuthorize("hasRole('RESTAURANT')")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant,
                                                    @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String ownerEmail = jwtUtil.getEmail(token);
        restaurant.setOwnerEmail(ownerEmail);

        Restaurant saved = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.ok(saved);
    }

    // ✅ Get all restaurants (public)
    @GetMapping("/public/all")
    public List<Restaurant> getAll() {
        return restaurantService.getAllRestaurants();
    }

    // ✅ Add menu item (restaurant must exist)
    @PostMapping("/{id}/menu")
    @PreAuthorize("hasRole('RESTAURANT')")
    public ResponseEntity<MenuItem> addMenuItem(@PathVariable Long id,
                                                @RequestBody MenuItem item) {
        MenuItem savedItem = restaurantService.addMenuItem(id, item);
        return ResponseEntity.ok(savedItem);
    }

    // ✅ Get menu for a restaurant (public)
    @GetMapping("/public/{id}/menu")
    public List<MenuItem> getMenu(@PathVariable Long id) {
        return restaurantService.getMenuItems(id);
    }
    
    @GetMapping("/api/restaurants/public/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id).orElseThrow();
    }

}
