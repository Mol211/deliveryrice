package com.mol211.deliveryrice.order.dto;

import com.mol211.deliveryrice.order.model.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        @Schema(
                description = "Identificador único del pedido.",
                example = "15"
        )
        Long id,

        @Schema(
                description = "Estado actual del pedido.",
                example = "PENDING"
        )
        OrderStatus orderStatus,

        @Schema(
                description = "Importe total del pedido.",
                example = "38.85"
        )
        BigDecimal total,

        @Schema(
                description = "Fecha y hora de creación del pedido.",
                example = "2026-07-03T20:15:00"
        )
        LocalDateTime createdAt,

        @Schema(
                description = "Identificador de la dirección de envío utilizada.",
                example = "1"
        )
        Long directionId,

        @Schema(
                description = "Productos incluidos en el pedido."
        )
        List<OrderItemResponse> items

) {
}
