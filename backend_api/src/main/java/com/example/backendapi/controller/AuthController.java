package com.example.backendapi.controller;

import com.example.backendapi.dto.AuthRequest;
import com.example.backendapi.dto.AuthResponse;
import com.example.backendapi.dto.RegisterRequest;
import com.example.backendapi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * PUBLIC_INTERFACE
 * Authentication endpoints (register and login), providing JWT tokens.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "User authentication and JWT issuance")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) { this.authService = authService; }

    // PUBLIC_INTERFACE
    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Registers a new user with the USER role and returns a JWT.")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    // PUBLIC_INTERFACE
    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticates user and returns a JWT.")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
