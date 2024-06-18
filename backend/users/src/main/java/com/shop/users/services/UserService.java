package com.shop.users.services;

import com.shop.users.dto.UserDto;
import com.shop.users.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean createUser(UserDto userDTO);

    User authenticate(String email, String password);
}
