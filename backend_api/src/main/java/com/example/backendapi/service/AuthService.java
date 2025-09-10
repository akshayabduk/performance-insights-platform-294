package com.example.backendapi.service;

import com.example.backendapi.dto.AuthRequest;
import com.example.backendapi.dto.AuthResponse;
import com.example.backendapi.dto.RegisterRequest;
import com.example.backendapi.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * PUBLIC_INTERFACE
 * Service that handles registration and login operations.
 */
@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthService(AuthenticationManager authManager, JwtService jwtService, UserService userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    // PUBLIC_INTERFACE
    public AuthResponse register(RegisterRequest req) {
        var ua = userService.createUser(req.getEmail(), req.getPassword(), req.getFullName());
        String token = jwtService.generateToken(ua.getEmail(), userService.getRoleNames(ua));
        return new AuthResponse(token, ua.getEmail(), userService.getRoleNames(ua));
    }

    // PUBLIC_INTERFACE
    public AuthResponse login(AuthRequest req) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid email or password");
        }
        var ua = userService.findByEmail(req.getEmail()).orElseThrow();
        String token = jwtService.generateToken(ua.getEmail(), userService.getRoleNames(ua));
        return new AuthResponse(token, ua.getEmail(), userService.getRoleNames(ua));
    }
}
