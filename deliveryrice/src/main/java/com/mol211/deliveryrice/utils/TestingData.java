package com.mol211.deliveryrice.utils;

import com.mol211.deliveryrice.user.dto.RegisterUserRequest;
import com.mol211.deliveryrice.user.dto.UpdateUserRequest;
import com.mol211.deliveryrice.user.model.Role;
import com.mol211.deliveryrice.user.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestingData {
    public final static List<User> USERS = Arrays.asList(
            User.builder()
                    .id(1L)
                    .name("Victor")
                    .lastname("Molins")
                    .password("$2a$10$LynYUKHm6okgxNnoPW/FF..r1tjN57HjSlKega3RKcFTSiyvh05GC")
                    .createdAt(LocalDateTime.now())
                    .mail("victor@mail.com")
                    .phone("649784845")
                    .role(Role.ADMIN).build(),
            User.builder()
                    .id(2L)
                    .name("Jose")
                    .lastname("Perez")
                    .password("$2a$10$LynYUKHm6okgxNnoPW/FF..r1tjN57HjSlKega3RKcFTSiyvh05GC")
                    .createdAt(LocalDateTime.now())
                    .mail("jose@mail.com")
                    .phone("649784845")
                    .role(Role.CLIENT).build()

    );
    public static User crearUser001() {
        return User.builder()
                .id(1L)
                .name("Victor")
                .lastname("Molins")
                .password("$2a$10$LynYUKHm6okgxNnoPW/FF..r1tjN57HjSlKega3RKcFTSiyvh05GC")
                .createdAt(LocalDateTime.now())
                .mail("victor@mail.com")
                .phone("649784845")
                .role(Role.ADMIN).build();
    }
    public static final User USER_002 = User.builder()
            .id(2L)
            .name("Jose")
            .lastname("Perez")
            .password("$2a$10$LynYUKHm6okgxNnoPW/FF..r1tjN57HjSlKega3RKcFTSiyvh05GC")
            .createdAt(LocalDateTime.now())
            .mail("jose@mail.com")
            .phone("649784845")
            .role(Role.CLIENT).build();
    public static Optional<User> crearUserOpt001() {
        return Optional.of(User.builder()
                .id(1L)
                .name("Victor")
                .lastname("Molins")
                .password("$2a$10$LynYUKHm6okgxNnoPW/FF..r1tjN57HjSlKega3RKcFTSiyvh05GC")
                .createdAt(LocalDateTime.now())
                .mail("victor@mail.com")
                .phone("649784845")
                .role(Role.ADMIN).build());
    }
    public static Optional<User> crearUserOpt002() {
        return Optional.of(User.builder()
                .id(2L)
                .name("Jose")
                .lastname("Perez")
                .password("$2a$10$LynYUKHm6okgxNnoPW/FF..r1tjN57HjSlKega3RKcFTSiyvh05GC")
                .createdAt(LocalDateTime.now())
                .mail("jose@mail.com")
                .phone("649784845")
                .role(Role.CLIENT).build());
    }
    public static final RegisterUserRequest crearRegisterRequest(){
        return new RegisterUserRequest(
            "Victor","Molins","victor@mail.com","Password123.","649784845");
    }
    public static final UpdateUserRequest crearUpdateUserRequest(){
        return new UpdateUserRequest(
                "Pepe","Perez","pepe@mail.com","666454545");
    }

}
