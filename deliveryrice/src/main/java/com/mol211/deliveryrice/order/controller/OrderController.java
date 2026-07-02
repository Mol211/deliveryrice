package com.mol211.deliveryrice.order.controller;

import com.mol211.deliveryrice.order.dto.CreateOrderRequest;
import com.mol211.deliveryrice.order.dto.OrderResponse;
import com.mol211.deliveryrice.order.model.OrderStatus;
import com.mol211.deliveryrice.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //====================================//
           //USUARIO AUTENTICADO//
    //====================================//
    @GetMapping("/me")
    public ResponseEntity<List<OrderResponse>>getMyOrders(
            Authentication authentication
    ){
        return ResponseEntity.ok(orderService.getMyOrders(authentication.getName()));
    }
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            Authentication authentication) {
        OrderResponse orderCreated = orderService.createOrder(request, authentication.getName());
        return ResponseEntity.created(URI.create("/api/v1/orders/"+orderCreated.id())).body(orderCreated);
    }
    //======================================//
                   //ADMIN//
    //======================================//

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(
            @RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(orderService.getOrders(userId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateStatusOrder(
            @PathVariable Long id,
            @RequestParam OrderStatus status
            ) {
        return ResponseEntity.ok(orderService.updateStatusOrder(id, status));
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<OrderResponse> updateOrder(
//            @PathVariable Long id,
//            @Valid @RequestBody UpdateOrderRequest request) {
//        return ResponseEntity.ok(orderService.updateOrder(id, request));
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrder(
//            @PathVariable Long id
//    ) {
//        orderService.deleteOrder(id);
//        return ResponseEntity.noContent().build();
//    }


}
