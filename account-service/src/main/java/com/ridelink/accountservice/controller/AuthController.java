package com.ridelink.accountservice.controller;

import com.ridelink.accountservice.dto.RegisterRequest;
import com.ridelink.accountservice.dto.UserResponse;
import com.ridelink.accountservice.model.User;
import com.ridelink.accountservice.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
