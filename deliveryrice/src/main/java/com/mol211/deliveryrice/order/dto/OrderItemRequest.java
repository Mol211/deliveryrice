package com.mol211.deliveryrice.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderItemRequest(
        @Schema(
                description = "Identificador del producto.",
                example = "3"
        )
        @NotNull(message = "Debe indicar un producto")
        Long productId,

        @Schema(
                description = "Cantidad solicitada del producto.",
                example = "2"
        )
        @NotNull(message = "Debe indicar una cantidad")
        @Min(value = 1, message = "La cantidad debe ser como mínimo 1")
        Integer quantity
) {
}
