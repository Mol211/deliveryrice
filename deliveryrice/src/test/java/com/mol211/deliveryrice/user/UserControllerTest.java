package com.mol211.deliveryrice.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mol211.deliveryrice.auth.jwt.JwtService;
import com.mol211.deliveryrice.user.controller.UserController;
import com.mol211.deliveryrice.user.dto.UpdateUserRequest;
import com.mol211.deliveryrice.user.dto.UserResponse;
import com.mol211.deliveryrice.user.mapper.UserMapper;
import com.mol211.deliveryrice.user.model.User;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
    //getAllUsers() V
    //getUserById V
    //updateUser V
    //deleteUser V
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
        verify(userService).findAllUsers();

    }

    @Test
    @WithMockUser(username = "pepe@mail.com")
    void testGetUserById() throws Exception {
        //Given
        Long id = 1L;
        UserResponse response = UserMapper.toResponse(crearUserOpt001().orElseThrow());
        when(userService.findUserById(id)).thenReturn(response);

        //When
        mvc.perform(get("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))

        //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Victor"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
        verify(userService).findUserById(id);

    }
    @Test
    void testGetUserById_userNotAuth() throws Exception {

        mvc.perform(get("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        verifyNoInteractions(userService);
    }




    @Test
    @WithMockUser(
            username = "pepe@mail.com"
    )
    void testUpdateUser_Success() throws Exception {
        //Given
        Long id = 1L;
        UpdateUserRequest request = new UpdateUserRequest("Victor Actualizado","Molins",null,null);
        User user = crearUserOpt001().orElseThrow();
        user.setName("Victor Actualizado");
        UserResponse response = UserMapper.toResponse(user);
        when(userService.updateUser(id,request)).thenReturn(response);

        //When Then
        mvc.perform(patch("/api/v1/users/{id}",id)
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Victor Actualizado"))
                .andExpect(jsonPath("$.phone").value("649784845"))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
        verify(userService).updateUser(id, request);

    }

    @Test
    @WithMockUser(
            username = "pepe@mail.com"
    )
    void testDeleteUser() throws Exception {
        Long id = 1L;
        mvc.perform(delete("/api/v1/users/{id}",id)
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(userService).deleteUser(id);
    }

    @WithMockUser(username = "admin@mail.com")
    @Test
    void testGetCurrentUser() throws Exception {
        String mail = "admin@mail.com";
        UserResponse response = UserMapper.toResponse(crearUserOpt001().orElseThrow());
        when(userService.findByMail(mail)).thenReturn(response);

        mvc.perform(get("/api/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Victor"))
                .andExpect(content().json(
                        objectMapper.writeValueAsString(response)
                ));
        verify(userService).findByMail(mail);






    }
}
