package com.mol211.deliveryrice.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ChangePasswordRequest(
        @NotBlank(message = "La contraseña actual es obligatoria")
        @Schema(
                description = "Contraseña actual del usuario",
                example = "Password123"
        )
        String currentPassword,
        @NotBlank(message = "La nueva contraseña es obligatoria")
        @Schema(
                description = "Nueva contraseña del usuario",
                example = "NewPassword123"
        )
        @Pattern(
                regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$",
                message = "La nueva contraseña debe tener entre 8 y 16 caracteres, con al menos un número, una minúscula y una mayúscula"
        )
        String newPassword
) {
}
