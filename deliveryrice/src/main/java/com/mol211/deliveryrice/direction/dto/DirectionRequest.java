package com.mol211.deliveryrice.direction.dto;

import jakarta.validation.constraints.NotBlank;

public record DirectionRequest(
        @NotBlank(message="la calle es obligatoria")
        String street,
        @NotBlank(message="el codigo postal es obligatorio")
        String postalCode,
        @NotBlank(message="la ciudad es obligatoria")
        String city,
        String additionalInfo
) {
}
