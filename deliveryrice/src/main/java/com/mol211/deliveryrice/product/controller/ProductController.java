package com.mol211.deliveryrice.product.controller;

import com.mol211.deliveryrice.product.dto.ProductRequest;
import com.mol211.deliveryrice.product.dto.ProductResponse;
import com.mol211.deliveryrice.product.model.Category;
import com.mol211.deliveryrice.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(
        name = "Products",
        description = "Endpoints para la gestión y consulta de productos disponibles."
)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    @Operation(
            summary = "Obtener productos",
            description = "Devuelve la lista de productos disponibles. Permite filtrar opcionalmente por categoría."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Productos obtenidos correctamente"),
            @ApiResponse(responseCode = "400", description = "Categoría no válida"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(required = false) Category category
            ) {
        return ResponseEntity.ok(productService.getProducts(category));
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener producto por ID",
            description = "Devuelve la información de un producto a partir de su identificador."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @PostMapping()
    @Operation(
            summary = "Crear producto",
            description = "Crea un nuevo producto en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/api/v1/products/"+productResponse.id()))
                .body(productResponse);

    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar producto",
            description = "Actualiza la información de un producto existente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id,request));
    }
    @PatchMapping("/{id}/availability")
    @Operation(
            summary = "Actualizar disponibilidad",
            description = "Modifica el estado de disponibilidad de un producto."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disponibilidad actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de entrada no válidos"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductResponse> updateAvailability(@PathVariable Long id,
                                                              @RequestParam Boolean available) {
        return ResponseEntity.ok(productService.updateAvailability(id, available));
    }


}
