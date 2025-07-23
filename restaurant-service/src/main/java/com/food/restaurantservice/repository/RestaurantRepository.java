package com.food.restaurantservice.repository;

import com.food.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByOwnerEmail(String email);
}
