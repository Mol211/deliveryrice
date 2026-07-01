package com.mol211.deliveryrice.product.service;

import com.mol211.deliveryrice.exception.NotFoundException;
import com.mol211.deliveryrice.product.dto.ProductRequest;
import com.mol211.deliveryrice.product.dto.ProductResponse;
import com.mol211.deliveryrice.product.mapper.ProductMapper;
import com.mol211.deliveryrice.product.model.Category;
import com.mol211.deliveryrice.product.model.Product;
import com.mol211.deliveryrice.product.persistence.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponse> getProducts(Category category) {
       List<Product> products = category == null
               ? productRepository.findAll()
               : productRepository.findByCategory(category);
        return products.stream().map(ProductMapper::toResponse).toList();
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No se encuentra el producto"));
        return ProductMapper.toResponse(p);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = ProductMapper.toProduct(request);
        product.setAvailable(true);
        Product productSaved = productRepository.save(product);
        return ProductMapper.toResponse(productSaved);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se encuentra el producto introducido"));
        ProductMapper.updateProductFromRequest(product, request);
        Product productSaved = productRepository.save(product);
        return ProductMapper.toResponse(productSaved);
    }

    @Override
    public ProductResponse updateAvailability(Long id, Boolean available) {
        Product product = productRepository.findById(id)
                        .orElseThrow(()->new NotFoundException("No se encuentra el producto introducido"));
        product.setAvailable(available);
        Product productSaved = productRepository.save(product);
        return ProductMapper.toResponse(productSaved);
    }
}
