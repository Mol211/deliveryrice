package com.mol211.deliveryrice.product.dto;

import com.mol211.deliveryrice.product.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        @Schema(
                description = "Identificador único del producto.",
                example = "1"
        )
        Long id,
        @Schema(
                description = "Nombre del producto.",
                example = "Sushi variado"
        )
        String name,
        @Schema(
                description = "Descripción del producto.",
                example = "Sushi variado con diferentes tipos de pescado y vegetales."
        )
        String description,
        @Schema(
                description = "Precio del producto.",
                example = "15.99"
        )
        BigDecimal price,
        @Schema(
                description = "Stock del producto.",
                example = "10"
        )
        Integer stock,
        @Schema(
                description = "Categoría del producto.",
                example = "RICE"
        )
        Category category,
        @Schema(
                description = "Indica si el producto está disponible para su venta.",
                example = "true"
        )
        Boolean available,
        @Schema(
                description = "Fecha y hora de creación del producto.",
                example = "2026-07-03T18:30:00"
        )
        LocalDateTime createdAt,
        @Schema(
                description = "URL de la imagen del producto.",
                example = "https://example.com/images/sushi-variado.jpg"
        )
        String imageUrl
) {
}
