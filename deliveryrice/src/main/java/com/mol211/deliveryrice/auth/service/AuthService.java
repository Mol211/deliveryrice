package com.mol211.deliveryrice.auth.service;

import com.mol211.deliveryrice.auth.dto.AuthResponse;
import com.mol211.deliveryrice.auth.dto.ChangePasswordRequest;
import com.mol211.deliveryrice.auth.dto.LoginRequest;
import com.mol211.deliveryrice.user.dto.RegisterUserRequest;
import jakarta.validation.Valid;

public interface AuthService {
    AuthResponse register(@Valid RegisterUserRequest request);

    AuthResponse login(@Valid LoginRequest request);

    void changePassword(String mail, @Valid ChangePasswordRequest request);
}
