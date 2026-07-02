package com.mol211.deliveryrice.order.service;

import com.mol211.deliveryrice.order.dto.CreateOrderRequest;
import com.mol211.deliveryrice.order.dto.OrderResponse;
import com.mol211.deliveryrice.order.model.OrderStatus;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getOrders(Long id);

    List<OrderResponse> getMyOrders(String name);

    OrderResponse createOrder(@Valid CreateOrderRequest request, String mail) ;

    OrderResponse updateStatusOrder(Long id, OrderStatus status);


}
