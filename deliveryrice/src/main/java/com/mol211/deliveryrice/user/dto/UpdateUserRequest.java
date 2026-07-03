package com.mol211.deliveryrice.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

        @Schema(
                description = "Nombre del usuario.",
                example = "Víctor"
        )
        @Size(min = 3, max = 20)
        String name,

        @Schema(
                description = "Apellidos del usuario.",
                example = "Molins Martínez"
        )
        @Size(min = 3, max = 30)
        String lastname,

        @Schema(
                description = "Correo electrónico del usuario.",
                example = "victor@example.com"
        )
        @Email
        String mail,

        @Schema(
                description = "Número de teléfono del usuario.",
                example = "612345678"
        )
        @Size(min = 9, max = 15)
        String phone
) {
}
