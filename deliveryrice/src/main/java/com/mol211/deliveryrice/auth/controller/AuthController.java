package com.mol211.deliveryrice.auth.controller;

import com.mol211.deliveryrice.auth.dto.AuthResponse;
import com.mol211.deliveryrice.auth.dto.ChangePasswordRequest;
import com.mol211.deliveryrice.auth.dto.LoginRequest;
import com.mol211.deliveryrice.auth.service.AuthService;
import com.mol211.deliveryrice.user.dto.RegisterUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name="Authentication",
description = "Endpoints para autenticación y gestión de credenciales de los usuarios")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    /*
    * POST /auth/register
    * POST /auth/login
    * PATCH /auth/change-password
    * */
    @PostMapping("/login")
    @Operation(
            summary="Iniciar Sesión",
            description="Autentica a un usuario mediante correo electrónico y contraseña.Si las credenciales son válidas, devuelve un token JWT."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autenticación realizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
            ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    @Operation(
            summary="Registrar Usuario",
            description= "Crea un nuevo usuario en el sistema y devuelve un token JWT para comenzar la sesión."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "409", description = "El correo electrónico ya está registrado")
    })
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterUserRequest request
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }
    @PatchMapping("/change-password")
    @Operation(
            summary = "Cambiar contraseña",
            description = "Permite al usuario autenticado cambiar su contraseña proporcionando la contraseña actual."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contraseña modificada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    public ResponseEntity<Void> changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordRequest request) {
        authService.changePassword(authentication.getName(), request);
        return ResponseEntity.noContent().build();
    }

}
