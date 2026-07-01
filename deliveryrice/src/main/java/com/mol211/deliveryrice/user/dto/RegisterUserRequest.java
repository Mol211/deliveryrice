package com.mol211.deliveryrice.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message="El nombre es obligatorio")
        @Size(min = 3, max = 15, message = "El nombre debe tener entre 3 y 15 caracteres")
        String name,
        @NotBlank(message="El apellido es obligatorio")
        @Size(min = 3, max = 30, message = "El apellido debe tener entre 3 y 30 caracteres")
        String lastname,
        @NotBlank(message="El mail es obligatorio")
        @Email(message="El email no tiene un formato válido")
        String mail,
        @NotBlank(message="La contraseña es obligatoria")
        @Pattern(
                regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$",
                message = "La contraseña debe tener entre 8 y 16 caracteres, con al menos un número, una minúscula y una mayúscula"
        )
        String password,
        @NotBlank(message="El teléfono es obligatorio")
        @Size(min = 9, max = 15, message="El teléfono debe tener entre 9 y 15 caracteres")
        String phone
) {
}
