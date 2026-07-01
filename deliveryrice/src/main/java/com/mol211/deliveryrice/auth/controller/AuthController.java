package com.mol211.deliveryrice.auth.controller;

import com.mol211.deliveryrice.auth.dto.AuthResponse;
import com.mol211.deliveryrice.auth.dto.ChangePasswordRequest;
import com.mol211.deliveryrice.auth.dto.LoginRequest;
import com.mol211.deliveryrice.auth.service.AuthService;
import com.mol211.deliveryrice.user.dto.RegisterUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/v1/auth")
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
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
            ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterUserRequest request
            ) {
        return ResponseEntity.ok(authService.register(request));
    }
    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordRequest request) {
        authService.changePassword(authentication.getName(), request);
        return ResponseEntity.noContent().build();
    }

}
