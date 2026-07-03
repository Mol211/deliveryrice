package com.mol211.deliveryrice.direction.controller;

import com.mol211.deliveryrice.direction.dto.DirectionRequest;
import com.mol211.deliveryrice.direction.dto.DirectionResponse;
import com.mol211.deliveryrice.direction.service.DirectionService;
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

@RestController
@RequestMapping("/api/v1/directions")
@Tag(name = "Directions", description = "Endpoints para la gestión de direcciones del usuario autenticado")
public class DirectionController {
    private final DirectionService directionService;

    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener mis direcciones",
            description = "Devuelve todas las direcciones asociadas al usuario autenticado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Direcciones obtenidas correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    public ResponseEntity<List<DirectionResponse>> getMyDirections(Authentication authentication) {
        return ResponseEntity.ok(directionService.getMyDirections(authentication.getName()));
    }
    @GetMapping("/default")
    @Operation(
            summary = "Obtener dirección por defecto",
            description = "Devuelve la dirección marcada como predeterminada del usuario autenticado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dirección por defecto obtenida correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "404", description = "Dirección por defecto no encontrada")
    })
    public ResponseEntity<DirectionResponse> getDefaultDirection(Authentication authentication) {
        return ResponseEntity.ok(directionService.getDefaultDirection(authentication.getName()));
    }
    @PostMapping
    @Operation(
            summary = "Crear dirección",
            description = "Crea una nueva dirección para el usuario autenticado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Dirección creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    public ResponseEntity<DirectionResponse> createDirection(
            @Valid @RequestBody DirectionRequest request,
            Authentication authentication
            ) {
        DirectionResponse response = directionService.createDirection(request, authentication.getName());
        return ResponseEntity.created(URI.create("/api/v1/directions/"+response.id())).body(response);
    }
    @PatchMapping("/{id}/default")
    @Operation(
            summary = "Establecer dirección por defecto",
            description = "Marca una dirección del usuario autenticado como dirección predeterminada."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dirección por defecto actualizada correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    public ResponseEntity<DirectionResponse> setDefaultDirection(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                directionService.setDefaultDirection(id, authentication.getName())
        );
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar dirección",
            description = "Actualiza una dirección existente del usuario autenticado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dirección actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    public ResponseEntity<DirectionResponse> updateDirection(
            @PathVariable Long id,
            @Valid @RequestBody DirectionRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                directionService.updateDirection(id, request, authentication.getName())
        );
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void>deleteDirection(
//            @PathVariable Long id,
//            Authentication authentication
//    ) {
//        directionService.deleteDirection(id, authentication.getName());
//        return ResponseEntity.noContent().build();
//    }



}
