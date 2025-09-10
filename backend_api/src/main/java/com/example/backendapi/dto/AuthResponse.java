package com.example.backendapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

/**
 * PUBLIC_INTERFACE
 * Auth response containing JWT and basic user info.
 */
public class AuthResponse {
    @Schema(description = "JWT token for subsequent API calls")
    private String token;

    @Schema(description = "User identity email")
    private String email;

    @Schema(description = "User roles")
    private Set<String> roles;

    public AuthResponse() {}
    public AuthResponse(String token, String email, Set<String> roles) {
        this.token = token;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public String getEmail() { return email; }
    public Set<String> getRoles() { return roles; }
    public void setToken(String token) { this.token = token; }
    public void setEmail(String email) { this.email = email; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
