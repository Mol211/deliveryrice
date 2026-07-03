package com.mol211.deliveryrice.user.dto;

import com.mol211.deliveryrice.user.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UserResponse(
        @Schema(
                description = "Identificador único del usuario.",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nombre del usuario.",
                example = "Víctor"
        )
        String name,

        @Schema(
                description = "Apellidos del usuario.",
                example = "Molins Martínez"
        )
        String lastname,

        @Schema(
                description = "Correo electrónico del usuario.",
                example = "victor@example.com"
        )
        String mail,

        @Schema(
                description = "Número de teléfono del usuario.",
                example = "612345678"
        )
        String phone,

        @Schema(
                description = "Rol asignado al usuario.",
                example = "USER"
        )
        Role role,

        @Schema(
                description = "Fecha y hora de creación del usuario.",
                example = "2026-07-03T12:30:00"
        )
        LocalDateTime createdAt
) {
}
