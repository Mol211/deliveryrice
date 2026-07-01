package com.mol211.deliveryrice.user.service;

import com.mol211.deliveryrice.exception.NotFoundException;
import com.mol211.deliveryrice.user.dto.UpdateUserRequest;
import com.mol211.deliveryrice.user.dto.UserResponse;
import com.mol211.deliveryrice.user.mapper.UserMapper;
import com.mol211.deliveryrice.user.model.User;
import com.mol211.deliveryrice.user.persistence.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toResponse).toList();
    }

    @Override
    public UserResponse findUserById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Usuario no encontrado"));
        return UserMapper.toResponse(u);
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User u = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Usuario no encontrado"));
        UserMapper.updateUserFromRequest(u, request);
        User updatedUser = userRepository.save(u);
        return UserMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        userRepository.delete(user);
    }

    @Override
    public UserResponse findByMail(String mail) {
        User u = userRepository.findByMail(mail)
                .orElseThrow(()->new NotFoundException("Mail no encontrado"));
        return UserMapper.toResponse(u);
    }
}
