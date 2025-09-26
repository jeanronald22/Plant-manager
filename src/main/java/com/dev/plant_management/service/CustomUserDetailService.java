package com.dev.plant_management.service;

import com.dev.plant_management.entity.UserEntity;
import com.dev.plant_management.exceptions.PlantAlreadyExistsException;
import com.dev.plant_management.exceptions.PlantNotFoundException;
import com.dev.plant_management.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.dev.plant_management.exceptions.PlantErrorCode.*;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user with username: {}", username);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("User not found with username: {}", username);
                    return new PlantNotFoundException(DATA_ERROR_USER_NOT_FOUND);
                });

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .build();

        return userDetails;
    }

    public UserEntity registration(UserEntity user) {
        logger.info("Attempting to register user with username: {}", user.getUsername());

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            logger.error("Registration failed: Email {} already in use", user.getEmail());
            throw new PlantAlreadyExistsException(DATA_ERROR_EMAIL_ALREADY_TAKEN);
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            logger.error("Registration failed: Username {} already taken", user.getUsername());
            throw new PlantAlreadyExistsException(DATA_ERROR_USERNAME_ALREADY_TAKEN);
        }

        // Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity savedUser = userRepository.save(user);

        logger.info("User registered successfully with id: {}", savedUser.getId());
        return savedUser;
    }
}
