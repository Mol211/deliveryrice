package com.mol211.deliveryrice.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message="El email es obligatorio")
        @Email(message="El email no tiene un formato válido")
        String mail,
        @NotBlank(message="La contraseña es obligatoria")
        String password
) {
}
