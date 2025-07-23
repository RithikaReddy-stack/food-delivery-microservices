package com.food.orderservice.dto;

import com.food.orderservice.model.OrderItem;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private Long restaurantId;
    private List<OrderItem> items;
}
