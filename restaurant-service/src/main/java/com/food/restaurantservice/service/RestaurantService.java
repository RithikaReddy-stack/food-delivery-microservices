package com.food.restaurantservice.service;

import com.food.restaurantservice.model.MenuItem;
import com.food.restaurantservice.model.Restaurant;
import com.food.restaurantservice.repository.MenuItemRepository;
import com.food.restaurantservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepo;
    private final MenuItemRepository menuRepo;

    // Add a new restaurant
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    // Get all restaurants (public)
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }

    // Get restaurant by ID
    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepo.findById(id);
    }

    // Add menu item to a restaurant
    public MenuItem addMenuItem(Long restaurantId, MenuItem item) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurantId));
        item.setRestaurant(restaurant);
        return menuRepo.save(item);
    }

    // Get menu items for a restaurant
    public List<MenuItem> getMenuItems(Long restaurantId) {
        return menuRepo.findByRestaurantId(restaurantId);
    }
}
