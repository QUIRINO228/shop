package com.shop.authenticationservice.services;

import com.shop.authenticationservice.model.AuthRequest;
import com.shop.authenticationservice.model.AuthResponse;
import com.shop.users.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    public AuthResponse authenticate(AuthRequest request) {
        try {
            User registeredUser = restTemplate.postForObject("http://localhost:8222/users/authenticate", request, User.class);
            if (registeredUser != null) {
                String role = (registeredUser.getRole() != null) ? registeredUser.getRole().toString() : "USER";

                String accessToken = jwtUtil.generate(registeredUser.getId(), role, "ACCESS");
                String refreshToken = jwtUtil.generate(registeredUser.getId(), role, "REFRESH");
                log.info("User registered successfully: {}", registeredUser.getEmail());
                return new AuthResponse(accessToken, refreshToken);
            } else {
                throw new RuntimeException("User registration failed");
            }
        } catch (HttpClientErrorException.BadRequest ex) {
            log.error("User registration failed: {}", ex.getResponseBodyAsString());
            throw new RuntimeException("User registration failed: " + ex.getResponseBodyAsString());
        }
    }
}
