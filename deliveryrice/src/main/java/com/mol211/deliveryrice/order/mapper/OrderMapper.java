package com.mol211.deliveryrice.order.mapper;

import com.mol211.deliveryrice.order.dto.OrderItemResponse;
import com.mol211.deliveryrice.order.dto.OrderResponse;
import com.mol211.deliveryrice.order.model.Order;

import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {
    public static OrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(OrderItemMapper::toResponse)
                .toList();
        return new OrderResponse(
          order.getId(),
          order.getOrderStatus(),
          order.getTotal(),
          order.getCreatedAt(),
          order.getDirection().getId(),
          items
        );
    }
}
