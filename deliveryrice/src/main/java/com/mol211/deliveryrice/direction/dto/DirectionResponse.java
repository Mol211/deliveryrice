package com.mol211.deliveryrice.direction.dto;

import java.time.LocalDateTime;

public record DirectionResponse(
        Long id,
        String street,
        String postalCode,
        String city,
        String additionalInfo,
        boolean defaultAddress,
        LocalDateTime createdAt) { }
