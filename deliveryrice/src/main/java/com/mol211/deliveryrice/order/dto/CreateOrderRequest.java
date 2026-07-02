package com.mol211.deliveryrice.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        @NotNull(message = "Debe indicar una dirección de envío")
        Long directionId,
        @NotNull(message="Debe indicar al menos un item válido")
        @NotEmpty(message="Debe indicar al menos un item válido")
        List<@Valid OrderItemRequest> items
) {
}
