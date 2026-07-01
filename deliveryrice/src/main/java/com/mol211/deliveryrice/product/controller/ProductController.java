package com.mol211.deliveryrice.product.controller;

import com.mol211.deliveryrice.product.dto.ProductRequest;
import com.mol211.deliveryrice.product.dto.ProductResponse;
import com.mol211.deliveryrice.product.model.Category;
import com.mol211.deliveryrice.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(required = false) Category category
            ) {
        return ResponseEntity.ok(productService.getProducts(category));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/api/v1/products/"+productResponse.id()))
                .body(productResponse);

    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id,request));
    }
    @PatchMapping("/{id}/availability")
    public ResponseEntity<ProductResponse> updateAvailability(@PathVariable Long id,
                                                              @RequestParam Boolean available) {
        return ResponseEntity.ok(productService.updateAvailability(id, available));
    }


}
