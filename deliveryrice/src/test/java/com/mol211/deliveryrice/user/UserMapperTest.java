package com.mol211.deliveryrice.user;

import com.mol211.deliveryrice.user.dto.RegisterUserRequest;
import com.mol211.deliveryrice.user.dto.UpdateUserRequest;
import com.mol211.deliveryrice.user.dto.UserResponse;
import com.mol211.deliveryrice.user.mapper.UserMapper;
import com.mol211.deliveryrice.user.model.Role;
import com.mol211.deliveryrice.user.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.mol211.deliveryrice.utils.TestingData.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {
    @Test
    void testToEntity_Success() {
        //Given
        RegisterUserRequest request = crearRegisterRequest();
        //When
        User user =  UserMapper.toEntity(request);
        //Then
        assertNotNull(user);
        assertNull(user.getId());
        assertEquals("Victor",user.getName());
        assertEquals(Role.CLIENT, user.getRole());

    }

    @Test
    void testToResponse() {
        //Given
        User user = crearUser001();
        //When
        UserResponse response = UserMapper.toResponse(user);
        //Then
        assertNotNull(response);
        assertAll(
                ()->assertEquals(1L, response.id()),
                ()->assertEquals("Victor",response.name()),
                ()->assertEquals(user.getCreatedAt(),response.createdAt())
        );
    }

    @Test
    void testUpdateUserFromRequest_allValues() {
        //Given
        UpdateUserRequest request = crearUpdateUserRequest();
        User user = crearUser001();
        //When
        UserMapper.updateUserFromRequest(user,request);

        assertEquals("Pepe",user.getName());
        assertEquals("Perez",user.getLastname());
        assertEquals("pepe@mail.com",user.getMail());
        assertEquals("666454545", user.getPhone());

    }

    @Test
    void testUpdateUserFromRequest_oneParameterNull() {
        //Given
        UpdateUserRequest request = new UpdateUserRequest(null,"Perez","pepe@mail.com","666454545");
        User user = crearUser001();
        //When
        UserMapper.updateUserFromRequest(user,request);

        assertEquals("Victor",user.getName());
        assertEquals("Perez",user.getLastname());
        assertEquals("pepe@mail.com",user.getMail());
        assertEquals("666454545", user.getPhone());
    }
    @Test
    void testUpdateUserFromRequest_twoParameterNull() {
        //Given
        UpdateUserRequest request = new UpdateUserRequest("Pepe",null,null,"666454545");
        User user = crearUser001();
        //When
        UserMapper.updateUserFromRequest(user,request);

        assertEquals("Pepe",user.getName());
        assertEquals("Molins",user.getLastname());
        assertEquals("victor@mail.com",user.getMail());
        assertEquals("666454545", user.getPhone());
    }
    @Test
    void testUpdateUserFromRequest_AllParameterNull() {
        //Given
        UpdateUserRequest request = new UpdateUserRequest(null,null,null,null);
        User user = crearUser001();
        //When
        UserMapper.updateUserFromRequest(user,request);

        assertEquals("Victor",user.getName());
        assertEquals("Molins",user.getLastname());
        assertEquals("victor@mail.com",user.getMail());
        assertEquals("649784845", user.getPhone());
    }
}
