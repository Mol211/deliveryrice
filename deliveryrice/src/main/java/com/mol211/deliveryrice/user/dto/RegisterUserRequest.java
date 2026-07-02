package com.mol211.deliveryrice.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message="El nombre es obligatorio")
        @Size(min = 3, max = 15, message = "El nombre debe tener entre 3 y 15 caracteres")
        @Schema(
                description = "Nombre del usuario",
                example = "Victor"
        )
        String name,
        @NotBlank(message="El apellido es obligatorio")
        @Size(min = 3, max = 30, message = "El apellido debe tener entre 3 y 30 caracteres")
        @Schema(
                description = "Apellidos del usuario",
                example = "Molins Martínez"
        )
        String lastname,
        @NotBlank(message="El mail es obligatorio")
        @Email(message="El email no tiene un formato válido")
        @Schema(
                description = "Correo electrónico del usuario",
                example = "victor@email.com"
        )
        String mail,
        @NotBlank(message="La contraseña es obligatoria")
        @Pattern(
                regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$",
                message = "La contraseña debe tener entre 8 y 16 caracteres, con al menos un número, una minúscula y una mayúscula"
        )
        @Schema(
                description = "Contraseña del usuario",
                example = "Password123"
        )
        String password,
        @Schema(
                description = "Número de teléfono",
                example = "600123456"
        )
        @NotBlank(message="El teléfono es obligatorio")
        @Size(min = 9, max = 15, message="El teléfono debe tener entre 9 y 15 caracteres")
        String phone
) {
}
