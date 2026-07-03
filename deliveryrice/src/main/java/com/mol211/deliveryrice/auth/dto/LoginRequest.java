package com.mol211.deliveryrice.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message="El email es obligatorio")
        @Email(message="El email no tiene un formato válido")
        @Schema(
                description = "Correo electrónico del usuario",
                example = "user@example.com")
        String mail,
        @NotBlank(message="La contraseña es obligatoria")
        @Schema(
                description = "Contraseña del usuario",
                example = "Password123"
        )
        String password
) {
}
