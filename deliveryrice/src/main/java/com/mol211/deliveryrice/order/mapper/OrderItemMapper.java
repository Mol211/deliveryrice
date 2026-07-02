package com.mol211.deliveryrice.order.mapper;

import com.mol211.deliveryrice.order.dto.OrderItemResponse;
import com.mol211.deliveryrice.order.model.OrderItem;

public class OrderItemMapper {
    public static OrderItemResponse toResponse(OrderItem item) {
        return new OrderItemResponse(
                item.getProduct().getId(),
                item.getProductName(),
                item.getProductQuantity(),
                item.getUnitPrice(),
                item.getSubtotal()
        );
    }
}
