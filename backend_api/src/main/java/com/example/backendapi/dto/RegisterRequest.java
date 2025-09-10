package com.example.backendapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * PUBLIC_INTERFACE
 * Registration request for new users.
 */
public class RegisterRequest {
    @Email @NotBlank
    @Schema(description = "Email address")
    private String email;

    @NotBlank @Size(min = 8, max = 100)
    @Schema(description = "Password (min 8 characters)")
    private String password;

    @NotBlank
    @Schema(description = "Full name of the user")
    private String fullName;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}
