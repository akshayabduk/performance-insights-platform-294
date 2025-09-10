package com.example.backendapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * PUBLIC_INTERFACE
 * Request to update a user's role set.
 */
public class UpdateRoleRequest {
    @NotNull
    @Schema(description = "Set admin to true to grant admin role, false to revoke")
    private Boolean admin;

    public Boolean getAdmin() { return admin; }
    public void setAdmin(Boolean admin) { this.admin = admin; }
}
