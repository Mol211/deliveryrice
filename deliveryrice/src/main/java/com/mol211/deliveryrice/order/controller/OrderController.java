package com.mol211.deliveryrice.order.controller;

import com.mol211.deliveryrice.order.dto.CreateOrderRequest;
import com.mol211.deliveryrice.order.dto.OrderResponse;
import com.mol211.deliveryrice.order.model.OrderStatus;
import com.mol211.deliveryrice.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(
        name = "Orders",
        description = "Endpoints para la creación, consulta y gestión de pedidos"
)
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
    @Operation(
            summary = "Obtener mis pedidos",
            description = "Devuelve todos los pedidos realizados por el usuario autenticado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedidos obtenidos correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    @GetMapping("/me")
    public ResponseEntity<List<OrderResponse>>getMyOrders(
            Authentication authentication
    ){
        return ResponseEntity.ok(orderService.getMyOrders(authentication.getName()));
    }
    @Operation(
            summary = "Crear pedido",
            description = "Crea un nuevo pedido para el usuario autenticado a partir de una dirección y una lista de productos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "404", description = "Dirección o producto no encontrado")
    })
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

    @Operation(
            summary = "Obtener pedidos",
            description = "Devuelve la lista de pedidos registrados en el sistema. Permite filtrar opcionalmente por usuario."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedidos obtenidos correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(
            @RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(orderService.getOrders(userId));
    }

    @Operation(
            summary = "Actualizar estado del pedido",
            description = "Modifica el estado de un pedido existente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado del pedido actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Estado de pedido no válido"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
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
