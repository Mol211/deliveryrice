package com.mol211.deliveryrice.config;

import com.mol211.deliveryrice.exception.NotFoundException;
import com.mol211.deliveryrice.user.persistence.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {
    //¿Dónde encuentro al usuario?
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username ->
            userRepository.findByMail(username)
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }
    //Cuando alguien pida passwordEncoder entrega este de aquí
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Crea y configura DaoAuthenticationProvider
    @Bean
    AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,
                                                  UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    //Recibie petición de auth y delega en authprovider adecuado.
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
