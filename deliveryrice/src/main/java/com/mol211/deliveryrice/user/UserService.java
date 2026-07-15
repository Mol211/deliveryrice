package com.mol211.deliveryrice.user;

import com.mol211.deliveryrice.user.dto.UpdateUserRequest;
import com.mol211.deliveryrice.user.dto.UserResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long id);

    UserResponse updateUser(Long id, @Valid UpdateUserRequest request);

    void deleteUser(Long id);

    UserResponse findByMail(String name);
}
