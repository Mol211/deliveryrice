package com.mol211.deliveryrice.product.service;

import com.mol211.deliveryrice.product.dto.ProductRequest;
import com.mol211.deliveryrice.product.dto.ProductResponse;
import com.mol211.deliveryrice.product.model.Category;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getProducts(Category category);

    ProductResponse getProduct(Long id);

    ProductResponse createProduct(@Valid ProductRequest request);

    ProductResponse updateProduct(Long id, @Valid ProductRequest request);

    ProductResponse updateAvailability(Long id, Boolean available);
}
