package com.mol211.deliveryrice.order.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long productId,
        String productName,
        Integer productQuantity,
        BigDecimal unitPrice,
        BigDecimal subtotal) {
}
