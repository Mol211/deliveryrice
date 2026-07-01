package com.mol211.deliveryrice.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @Size(min = 3, max = 20) String name,
        @Size(min = 3, max = 30) String lastname,
        @Email String mail,
        @Size(min = 9, max= 15) String phone
) {
}
