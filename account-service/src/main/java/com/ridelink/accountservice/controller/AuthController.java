package com.ridelink.accountservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridelink.accountservice.dto.LoginRequest;
import com.ridelink.accountservice.dto.LoginResponse;
import com.ridelink.accountservice.dto.RegisterRequest;
import com.ridelink.accountservice.dto.UserResponse;
import com.ridelink.accountservice.model.User;
import com.ridelink.accountservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        User registeredUser = userService.registerUser(request);

        UserResponse response = new UserResponse(
            registeredUser.getId(),
            registeredUser.getName(),
            registeredUser.getEmail(),
            registeredUser.getRole(),
            registeredUser.getStatus()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user = userService.loginUser(request);

        LoginResponse response = new LoginResponse(
            "Login successful",
            null,
            user.getId(),
            user.getRole()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}