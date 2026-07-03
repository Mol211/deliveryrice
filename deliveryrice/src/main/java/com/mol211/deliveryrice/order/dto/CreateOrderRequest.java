package com.mol211.deliveryrice.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        @Schema(
                description = "Identificador de la dirección de envío.",
                example = "1"
        )
        @NotNull(message = "Debe indicar una dirección de envío")
        Long directionId,
        @Schema(
                description = "Lista de productos que formarán parte del pedido.",
                example = """
    [
      {
        "productId": 1,
        "quantity": 2
      },
      {
        "productId": 4,
        "quantity": 1
      }
    ]
    """
        )
        @NotNull(message="Debe indicar al menos un item válido")
        @NotEmpty(message="Debe indicar al menos un item válido")
        List<@Valid OrderItemRequest> items
) {
}
