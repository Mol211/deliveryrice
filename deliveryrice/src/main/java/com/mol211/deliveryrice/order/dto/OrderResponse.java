package com.mol211.deliveryrice.order.dto;

import com.mol211.deliveryrice.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        OrderStatus orderStatus,
        BigDecimal total,
        LocalDateTime createdAt,
        Long directionId,
        List<OrderItemResponse> items

) {
}
