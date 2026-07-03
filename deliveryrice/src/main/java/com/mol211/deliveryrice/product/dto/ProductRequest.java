package com.mol211.deliveryrice.product.dto;

import com.mol211.deliveryrice.product.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @Schema(
                description = "Nombre del producto.",
                example = "Sushi variado"
        )
        @NotBlank(message="El nombre es obligatorio")
        String name,
        @Schema(
                description = "Descripción del producto.",
                example = "Sushi variado con diferentes tipos de pescado y vegetales."
        )
        @NotBlank(message="la descripción es obligatoria")
        String description,
        @Schema(
                description = "Precio del producto.",
                example = "15.99"
        )
        @NotNull(message="El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
        @Digits(integer = 8, fraction = 2)
        BigDecimal price,
        @Schema(
                description = "Stock del producto.",
                example = "10"
        )
        @NotNull(message="El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,
        @Schema(
                description = "Categoría del producto.",
                example = "RICE"
        )
        @NotNull(message="La categoría es obligatoria")
        Category category,
        @Schema(
                description = "URL de la imagen del producto.",
                example = "https://example.com/image.jpg"
        )
        String imageUrl

) {
}
