package com.mol211.deliveryrice.product.mapper;

import com.mol211.deliveryrice.product.dto.ProductRequest;
import com.mol211.deliveryrice.product.dto.ProductResponse;
import com.mol211.deliveryrice.product.model.Product;

public class ProductMapper {
    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getAvailable(),
                product.getCreatedAt(),
                product.getImageUrl()
        );
        
    }

    public static Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stock(request.stock())
                .category(request.category())
                .imageUrl(request.imageUrl())
                .build();
    }

    public static void updateProductFromRequest(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(request.category());
        product.setImageUrl(request.imageUrl());

    }
}
