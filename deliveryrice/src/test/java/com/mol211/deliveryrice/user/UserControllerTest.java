package com.mol211.deliveryrice.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mol211.deliveryrice.auth.jwt.JwtService;
import com.mol211.deliveryrice.user.controller.UserController;
import com.mol211.deliveryrice.user.dto.UserResponse;
import com.mol211.deliveryrice.user.mapper.UserMapper;
import com.mol211.deliveryrice.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.mol211.deliveryrice.utils.TestingData.crearUserOpt001;
import static com.mol211.deliveryrice.utils.TestingData.crearUserOpt002;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private UserService userService;
    @MockitoBean
    private JwtService jwtService;
    @Autowired
    ObjectMapper objectMapper;


    //TODO:Tenemos que probar
    //getAllUsers()
    //getUserById
    //updateUser
    //deleteUser
    //getCurrentUser

    @Test
    @WithMockUser(username = "pepe@mail.com")
    void testGetAllUsers() throws Exception {
        //Given
        List<User> users = Arrays.asList(crearUserOpt001().orElseThrow(),crearUserOpt002().orElseThrow());
       List<UserResponse> responses = users.stream().map(UserMapper::toResponse).toList();
        when(userService.findAllUsers()).thenReturn(responses);
        //When
            mvc.perform(get("/api/v1/users")
                    .contentType(MediaType.APPLICATION_JSON))
            //Then
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].name").value("Victor"))
                    .andExpect(jsonPath("$[1].name").value("Jose"))
                    .andExpect(jsonPath("$[0].id").value(1L))
                    .andExpect(jsonPath("$[1].id").value(2L))
                    .andExpect(content().json(objectMapper.writeValueAsString(responses)));


    }
}
