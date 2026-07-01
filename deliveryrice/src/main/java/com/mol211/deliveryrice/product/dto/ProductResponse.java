package com.mol211.deliveryrice.product.dto;

import com.mol211.deliveryrice.product.model.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Category category,
        Boolean available,
        LocalDateTime createdAt,
        String imageUrl
) {
}
