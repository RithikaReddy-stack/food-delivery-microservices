package com.food.orderservice.dto;

import lombok.Data;

@Data
public class MenuItemDTO {
    private Long id;
    private String name;
    private double price;
}
