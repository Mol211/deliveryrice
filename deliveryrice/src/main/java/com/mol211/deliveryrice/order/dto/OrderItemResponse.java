package com.mol211.deliveryrice.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record OrderItemResponse(
        @Schema(
                description = "Identificador del producto.",
                example = "3"
        )
        Long productId,

        @Schema(
                description = "Nombre del producto.",
                example = "Sushi variado"
        )
        String productName,

        @Schema(
                description = "Cantidad del producto incluida en el pedido.",
                example = "2"
        )
        Integer productQuantity,

        @Schema(
                description = "Precio unitario del producto.",
                example = "12.95"
        )
        BigDecimal unitPrice,

        @Schema(
                description = "Importe total de este producto en el pedido.",
                example = "25.90"
        )
        BigDecimal subtotal) {
}
