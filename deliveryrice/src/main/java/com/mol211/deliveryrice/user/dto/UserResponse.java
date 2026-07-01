package com.mol211.deliveryrice.user.dto;

import com.mol211.deliveryrice.user.model.Role;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String lastname,
        String mail,
        String phone,
        Role role,
        LocalDateTime createdAt
) {
}
