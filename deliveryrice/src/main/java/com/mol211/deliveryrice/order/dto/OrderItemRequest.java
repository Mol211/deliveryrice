package com.mol211.deliveryrice.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderItemRequest(
        @NotNull(message="Debe indicar un producto")
        Long productId,
        @NotNull(message="Debe indicar una cantidad")
        @Min(value=1, message = "La cantidad deb ser como mínimo 1")
        Integer quantity
) {
}
