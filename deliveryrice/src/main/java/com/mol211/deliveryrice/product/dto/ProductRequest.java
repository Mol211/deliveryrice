package com.mol211.deliveryrice.product.dto;

import com.mol211.deliveryrice.product.model.Category;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message="El nombre es obligatorio")
        String name,
        @NotBlank(message="la descripción es obligatoria")
        String description,
        @NotNull(message="El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
        @Digits(integer = 8, fraction = 2)
        BigDecimal price,
        @NotNull(message="El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,
        @NotNull(message="La categoría es obligatoria")
        Category category,
        String imageUrl

) {
}
