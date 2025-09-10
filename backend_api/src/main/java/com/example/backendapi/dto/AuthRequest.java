package com.example.backendapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * PUBLIC_INTERFACE
 * Auth request for login.
 */
public class AuthRequest {
    @Schema(description = "User email")
    @Email @NotBlank
    private String email;

    @Schema(description = "User password")
    @NotBlank
    private String password;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
