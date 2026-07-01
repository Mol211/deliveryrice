package com.mol211.deliveryrice.user.mapper;
import com.mol211.deliveryrice.user.dto.RegisterUserRequest;
import com.mol211.deliveryrice.user.dto.UpdateUserRequest;
import com.mol211.deliveryrice.user.dto.UserResponse;
import com.mol211.deliveryrice.user.model.Role;
import com.mol211.deliveryrice.user.model.User;
import jakarta.persistence.*;
public class UserMapper {
    public static User toEntity(RegisterUserRequest request){
        return User.builder()
                .name(request.name())
                .lastname(request.lastname())
                .mail(request.mail())
                .phone(request.phone())
                .role(Role.CLIENT)
                .build();
    }

    public static UserResponse toResponse(User user) {

        return new UserResponse(user.getId(), user.getName(), user.getLastname(), user.getMail(), user.getPhone(), user.getRole(), user.getCreatedAt());

    }

    public static void updateUserFromRequest(User user, UpdateUserRequest request){
        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.lastname() != null) {
            user.setLastname(request.lastname());
        }
        if (request.mail() != null) {
            user.setMail(request.mail());
        }
        if (request.phone() != null) {
            user.setPhone(request.phone());
        }
    }

}
