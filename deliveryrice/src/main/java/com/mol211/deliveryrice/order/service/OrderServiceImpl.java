package com.mol211.deliveryrice.order.service;

import com.mol211.deliveryrice.direction.model.Direction;
import com.mol211.deliveryrice.direction.persistence.DirectionRepository;
import com.mol211.deliveryrice.exception.BadRequestException;
import com.mol211.deliveryrice.exception.NotFoundException;
import com.mol211.deliveryrice.order.dto.CreateOrderRequest;
import com.mol211.deliveryrice.order.dto.OrderResponse;
import com.mol211.deliveryrice.order.mapper.OrderMapper;
import com.mol211.deliveryrice.order.model.Order;
import com.mol211.deliveryrice.order.model.OrderItem;
import com.mol211.deliveryrice.order.model.OrderStatus;
import com.mol211.deliveryrice.order.repository.OrderRepository;
import com.mol211.deliveryrice.product.model.Product;
import com.mol211.deliveryrice.product.persistence.ProductRepository;
import com.mol211.deliveryrice.user.model.User;
import com.mol211.deliveryrice.user.persistence.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final DirectionRepository directionRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, DirectionRepository directionRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.directionRepository = directionRepository;
    }
    private User getAuthenticatedUser(String mail) {
        return userRepository.findByMail(mail)
                .orElseThrow(()->new NotFoundException("No se encontro usuario con ese mail"));
    }

    @Override
    public List<OrderResponse> getOrders(Long id) {
        List<Order> orders = id == null
                ? orderRepository.findAll()
                : orderRepository.findByUserId(id);
        return orders.stream()
                .map(OrderMapper::toResponse)
                .toList();

    }

    @Override
    public List<OrderResponse> getMyOrders(String mail) {
        User user = getAuthenticatedUser(mail);
        return orderRepository.findByUserId(user.getId()).stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest request, String mail) {
        User user = getAuthenticatedUser(mail);
        Direction direction = directionRepository.findByIdAndUserId(
                request.directionId(),
                user.getId()
        ).orElseThrow(()->new NotFoundException("No se encontró la dirección para ese usuario"));
        Order order = Order.builder()
                .user(user)
                .direction(direction)
                .orderStatus(OrderStatus.PENDING)
                .total(BigDecimal.ZERO)
                .items(new ArrayList<>())
                .build();
        BigDecimal total = BigDecimal.ZERO;
        for(var item:request.items()) {
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(()-> new NotFoundException("Producto con "+item.productId()+" no encontrado"));
            if(!product.getAvailable()) {
                throw new BadRequestException("El producto no está disponible");
            }
            if(product.getStock() < item.quantity()) {
                throw new BadRequestException("Stock insuficiente para "+product.getName());
            }
            BigDecimal unitPrice = product.getPrice();
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(item.quantity()));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .productName(product.getName())
                    .productQuantity(item.quantity())
                    .unitPrice(unitPrice)
                    .subtotal(subtotal)
                    .build();
            order.getItems().add(orderItem);
            product.setStock(product.getStock() - item.quantity());
            total = total.add(subtotal);
        }
        order.setTotal(total);
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toResponse(savedOrder);

    }

    @Override
    public OrderResponse updateStatusOrder(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se encontró el pedido a modificar"));

        order.setOrderStatus(status);
        return OrderMapper.toResponse(order);
    }


}
