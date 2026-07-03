package com.mol211.deliveryrice.direction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record DirectionRequest(

        @Schema(description = "Calle y número de la dirección.", example = "Calle Larga 25")
        @NotBlank(message = "la calle es obligatoria")
        String street,

        @Schema(description = "Código postal de la dirección.", example = "11403")
        @NotBlank(message = "el codigo postal es obligatorio")
        String postalCode,

        @Schema(description = "Ciudad de la dirección.", example = "Jerez de la Frontera")
        @NotBlank(message = "la ciudad es obligatoria")
        String city,

        @Schema(description = "Información adicional de la dirección.", example = "Piso 2, puerta B")
        String additionalInfo
) {
}
