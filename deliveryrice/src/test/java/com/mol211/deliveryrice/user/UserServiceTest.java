package com.mol211.deliveryrice.user;

import com.mol211.deliveryrice.exception.NotFoundException;
import com.mol211.deliveryrice.user.dto.UpdateUserRequest;
import com.mol211.deliveryrice.user.dto.UserResponse;
import com.mol211.deliveryrice.user.model.User;
import com.mol211.deliveryrice.user.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.mol211.deliveryrice.utils.TestingData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    void testFindAllUsers_returnsMappedList() {
        //Given
        List<User> usuarios = Arrays.asList(crearUserOpt001().orElseThrow(), crearUserOpt002().orElseThrow());
        when(userRepository.findAll()).thenReturn(usuarios);
        //When
        List<UserResponse> allUsers = userServiceImpl.findAllUsers();
        //Then
        assertFalse(allUsers.isEmpty());
        assertEquals(2, allUsers.size());
        assertAll(
                ()-> assertEquals(1L, allUsers.get(0).id()),
                ()-> assertEquals("jose@mail.com", allUsers.get(1).mail())
        );
        verify(userRepository).findAll();
    }
    @Test
    void testFindAllUsers_emptyList() {
        //Given
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        //When
        List<UserResponse> allUsers = userServiceImpl.findAllUsers();
        //Then
        assertTrue(allUsers.isEmpty());
        assertEquals(0, allUsers.size());
        verify(userRepository).findAll();
    }

    @Test
    void testFindUserById_Succes() {
        //Given
        Optional<User> user001 = crearUserOpt001();
        when(userRepository.findById(1L)).thenReturn(user001);

        //When
        UserResponse response001 = userServiceImpl.findUserById(1L);
        //Then
        assertNotNull(response001);
        assertAll(
                ()->assertEquals(1,response001.id()),
                ()->assertEquals("victor@mail.com",response001.mail())
        );
        verify(userRepository).findById(1L);
    }
    @Test
    void testFindUserById_NotFoundException() {
        //Given
        Optional<User> user001 = crearUserOpt001();
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        //When - Then
        assertThrows(NotFoundException.class,()->{
            userServiceImpl.findUserById(3L);
        });
        verify(userRepository).findById(3L);
    }

    @Test
    void testUpdateUser_NotFoundException() {
        //Given
        Long id = 1L;
        UpdateUserRequest request = new UpdateUserRequest("Victor","Molins","victor@mail.com","6464646645");
        String expectedErrorMessage = "Usuario no encontrado";
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        //When-Then
        Exception ex = assertThrows(NotFoundException.class, ()->{
            userServiceImpl.updateUser(id,request);
        });
        assertEquals(expectedErrorMessage,ex.getMessage());

    }

    @Test
    void testUpdateUser_Success() {
        //Given
        Long id = 1L;
        UpdateUserRequest request = new UpdateUserRequest("Pepe","Molins","pepe@mail.com","6464646645");
        Optional<User> user001 = crearUserOpt001();
        when(userRepository.findById(id)).thenReturn(user001);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //When
        UserResponse response =  userServiceImpl.updateUser(id,request);

        //Then
        assertAll(
                () ->assertEquals("Pepe",response.name()),
                ()->assertEquals(1L, response.id()),
                ()->assertEquals("pepe@mail.com", response.mail())
        );
        verify(userRepository).findById(id);
        verify(userRepository).save(any());

    }

    @Test
    void testDeleteUser_NotFoundException() {
        //Given
        Long id = 1L;
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception ex = assertThrows(NotFoundException.class,()->userServiceImpl.deleteUser(id));
        assertEquals("Usuario no encontrado", ex.getMessage());

        verify(userRepository).findById(id);
        verify(userRepository,never()).delete(any());
    }

    @Test
    void testDeleteUser_Success() {
        //Given
        Long id = 1L;
        Optional<User> user001 = crearUserOpt001();
        when(userRepository.findById(id)).thenReturn(user001);
        //When
        userServiceImpl.deleteUser(id);

        //Then
        verify(userRepository).findById(id);
        verify(userRepository).delete(any());


    }

    @Test
    void findByMail_Success() {
        //Given
        String mail = "victor@mail.com";
        Optional<User> user001 = crearUserOpt001();
        when(userRepository.findByMail(mail)).thenReturn(user001);

        //When
        UserResponse response = userServiceImpl.findByMail(mail);

        //Then
        assertNotNull(response);
        assertAll(
                ()-> assertEquals(mail,response.mail()),
                ()->assertEquals("Victor",response.name()),
                ()->assertEquals(1L,response.id())
        );
        verify(userRepository,times(1)).findByMail(any());

    }
    @Test
    void findByMail_NotFoundException() {
        //Given
        String mail = "admin@mail.com";
        when(userRepository.findByMail(mail)).thenReturn(Optional.empty());

        //When Then
        Exception ex = assertThrows(NotFoundException.class,()->{
            userServiceImpl.findByMail(mail);
        });
        assertEquals("Mail no encontrado", ex.getMessage());


    }
}