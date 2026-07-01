package com.mol211.deliveryrice.product.persistence;

import com.mol211.deliveryrice.product.model.Category;
import com.mol211.deliveryrice.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
