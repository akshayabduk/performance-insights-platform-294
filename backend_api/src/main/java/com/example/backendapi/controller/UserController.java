package com.example.backendapi.controller;

import com.example.backendapi.dto.UpdateRoleRequest;
import com.example.backendapi.model.UserAccount;
import com.example.backendapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Admin-only endpoints for managing users and roles.
 */
@RestController
@RequestMapping("/api/admin/users")
@Tag(name = "Users", description = "User management (admin)")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(summary = "List users", description = "List all registered users.")
    public ResponseEntity<List<UserAccount>> list() {
        return ResponseEntity.ok(userService.listUsers());
    }

    // PUBLIC_INTERFACE
    @PatchMapping("/{id}/role")
    @Operation(summary = "Update user roles", description = "Grant or revoke admin role.")
    public ResponseEntity<UserAccount> updateRoles(@PathVariable String id, @Valid @RequestBody UpdateRoleRequest req) {
        return ResponseEntity.ok(userService.updateUserRoles(id, req));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Delete a user by ID.")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
