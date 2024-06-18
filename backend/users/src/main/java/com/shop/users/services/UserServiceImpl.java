package com.shop.users.services;

import com.shop.sendgrid.dto.EmailDto;
import com.shop.users.client.SendGridClient;
import com.shop.users.dto.UserDto;
import com.shop.users.model.User;
import com.shop.users.model.enums.Role;
import com.shop.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SendGridClient sendGridClient;

    @Override
    public boolean createUser(UserDto userDTO) {
        String email = userDTO.getEmail();
        if (isEmailAlreadyInUse(email)) {
            log.warn("Email already in use: {}", email);
            return false;
        }
        User user = buildUserFromDto(userDTO);
        userRepository.save(user);
        log.info("Saving new user with : {}", email);
//        sendActivationEmail(user);    
        return true;
    }

    private User buildUserFromDto(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()))
                .role(Role.USER)
                .active(true)
                .activationLink(UUID.randomUUID().toString())
                .build();
    }


    public boolean isEmailAlreadyInUse(String email) {
        boolean emailExists = userRepository.findByEmail(email).isPresent();
        if (emailExists) {
            log.warn("Email already in use: {}", email);
        }
        return emailExists;
    }

    @Override
    public User authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                log.info("User authenticated successfully: {}", email);
                return user;
            }
        }
        log.warn("Authentication failed for user: {}", email);
        return null;
    }


    private void sendActivationEmail(User user) {
        EmailDto emailDto = EmailDto.builder()
                .toEmail(user.getEmail())
                .body(user.getActivationLink())
                .build();

        sendGridClient.sendActivatedLink(emailDto);
        log.info("Activation email sent to: {}", user.getEmail());
    }
}
