package com.dev.plant_management.controller;

import com.dev.plant_management.config.JwtUtil;
import com.dev.plant_management.entity.UserEntity;
import com.dev.plant_management.exceptions.PlantInvalidCredentials;
import com.dev.plant_management.exceptions.PlantRegistrationFaild;
import com.dev.plant_management.payload.request.LoginRequest;
import com.dev.plant_management.payload.response.RegisterResponse;
import com.dev.plant_management.service.CustomUserDetailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_INVALID_CREDENTIALS;
import static com.dev.plant_management.exceptions.PlantErrorCode.DATA_ERROR_REGISTRATION_FAILED;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        logger.info("Login attempt for user: {}", request.getUsername());
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            logger.info("Authentication successful for user: {}", request.getUsername());
            ResponseCookie jwtCookie = ResponseCookie.from("access", jwtUtil.generateToken(request.getUsername()))
                    .httpOnly(true)
                    .path("/")
                    .maxAge(24 * 60 * 60) // 1 jour
                    .sameSite("Strict")
                    .build();
            return ResponseEntity.ok().header("Set-Cookie", jwtCookie.toString()).body("authentification reussie");
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for user: {} | reason: {}", request.getUsername(), e.getMessage());
            throw new PlantInvalidCredentials(DATA_ERROR_INVALID_CREDENTIALS);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody UserEntity user) {
        var response = new RegisterResponse();
        logger.info("Registration attempt for user: {}", user.getUsername());
        try {
            UserEntity savedUser = customUserDetailService.registration(user);
            logger.info("User successfully registered: {}", savedUser.getUsername());
            response.setEmail(user.getEmail());
            response.setUsername(user.getUsername());

            // HttpOnlyCookie
            ResponseCookie jwtCookie = ResponseCookie.from("access", jwtUtil.generateToken(savedUser.getUsername()))
                    .httpOnly(true)
                    .path("/")
                    .maxAge(24 * 60 * 60) // 1 jour
                    .sameSite("Strict")
                    .build();
            return ResponseEntity.ok().header("Set-Cookie", jwtCookie.toString()).body(response);
        } catch (Exception e) {
            logger.error("Registration failed for user: {} | reason: {}", user.getUsername(), e.getMessage());
            throw new PlantRegistrationFaild(DATA_ERROR_REGISTRATION_FAILED);
        }
    }
}
