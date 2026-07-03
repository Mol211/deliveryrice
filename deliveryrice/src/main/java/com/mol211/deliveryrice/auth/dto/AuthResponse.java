package com.mol211.deliveryrice.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponse(
        @Schema(
                description = "JWT generado tras una autenticación correcta.",
                example = "eyJhbGciOiJIUzI1NiJ9..."
        )
        String token
) {
}
