package com.mol211.deliveryrice.direction.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record DirectionResponse(
        @Schema(description = "Identificador único de la dirección.", example = "1")
        Long id,

        @Schema(description = "Calle y número de la dirección.", example = "Calle Larga 25")
        String street,

        @Schema(description = "Código postal de la dirección.", example = "11403")
        String postalCode,

        @Schema(description = "Ciudad de la dirección.", example = "Jerez de la Frontera")
        String city,

        @Schema(description = "Información adicional de la dirección.", example = "Piso 2, puerta B")
        String additionalInfo,

        @Schema(description = "Indica si la dirección es la predeterminada del usuario.", example = "true")
        boolean defaultAddress,

        @Schema(description = "Fecha y hora de creación de la dirección.", example = "2026-07-03T18:30:00")
        LocalDateTime createdAt) { }
