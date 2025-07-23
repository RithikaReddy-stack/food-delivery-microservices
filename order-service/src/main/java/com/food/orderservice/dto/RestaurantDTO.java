package com.food.orderservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private List<MenuItemDTO> menuItems;
}
