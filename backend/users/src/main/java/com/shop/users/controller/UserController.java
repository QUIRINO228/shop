package com.shop.users.controller;

import com.shop.users.dto.UserDto;
import com.shop.users.model.User;
import com.shop.users.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto)
                ? ResponseEntity.ok().body(Map.of("message", "Registration successful"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Registration failed"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody UserDto userDto) {
        User authenticatedUser = userService.authenticate(userDto.getEmail(), userDto.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok().body(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Authentication failed"));
        }
    }
}
