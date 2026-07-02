package com.mol211.deliveryrice.order.repository;

import com.mol211.deliveryrice.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long id);
}
