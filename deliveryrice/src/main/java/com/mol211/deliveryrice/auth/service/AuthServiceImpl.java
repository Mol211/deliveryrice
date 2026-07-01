package com.mol211.deliveryrice.auth.service;

import com.mol211.deliveryrice.auth.dto.AuthResponse;
import com.mol211.deliveryrice.auth.dto.ChangePasswordRequest;
import com.mol211.deliveryrice.auth.dto.LoginRequest;
import com.mol211.deliveryrice.auth.jwt.JwtService;
import com.mol211.deliveryrice.exception.EmailAlreadyExistsException;
import com.mol211.deliveryrice.exception.InvalidCredentialsException;
import com.mol211.deliveryrice.exception.NotFoundException;
import com.mol211.deliveryrice.user.dto.RegisterUserRequest;
import com.mol211.deliveryrice.user.mapper.UserMapper;
import com.mol211.deliveryrice.user.model.User;
import com.mol211.deliveryrice.user.persistence.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterUserRequest request) {
     if (userRepository.existsByMail(request.mail())) {
         throw new EmailAlreadyExistsException("El email ya está registrado");
     }
     var user = UserMapper.toEntity(request);
     user.setPassword(passwordEncoder.encode(request.password()));
     var userSaved = userRepository.save(user);

     String token = jwtService.generateToken(userSaved);
     return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.mail(),
                        request.password()
                )
        );
        var userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }

    @Override
    public void changePassword(String mail, ChangePasswordRequest request) {
        User u = userRepository.findByMail(mail).orElseThrow(()-> new NotFoundException("No se encontró el email"));
        boolean passwordMatches = passwordEncoder.matches(
                request.currentPassword(), u.getPassword()
        );
        if(!passwordMatches) {
            throw new InvalidCredentialsException("La contraseña actual no es correcta");
        }
        u.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(u);
    }
}
