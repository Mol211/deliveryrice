package com.mol211.deliveryrice.config;

import com.mol211.deliveryrice.auth.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authProvider
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/login",
                                "/api/v1/auth/register"
                        ).permitAll()

                        .requestMatchers("/api/v1/users/me").authenticated()
                        .requestMatchers("/api/v1/users/**").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/api/v1/products/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/v1/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/products/**").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST,"/api/v1/orders/**").authenticated()
                        .requestMatchers("/api/v1/orders/me").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/v1/orders/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/orders/**").hasAnyAuthority("ADMIN","DELIVER")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}