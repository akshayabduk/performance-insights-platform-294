package com.example.backendapi.controller;

import com.example.backendapi.dto.NotificationRequest;
import com.example.backendapi.model.Notification;
import com.example.backendapi.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Endpoints to manage notifications.
 */
@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notifications", description = "In-app notifications")
public class NotificationsController {

    private final NotificationService service;

    public NotificationsController(NotificationService service) { this.service = service; }

    // PUBLIC_INTERFACE
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Create notification", description = "Create a notification for a user (admin only).")
    public ResponseEntity<Notification> create(@Valid @RequestBody NotificationRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "List notifications", description = "List notifications for a user.")
    public ResponseEntity<List<Notification>> list(@PathVariable String userId) {
        return ResponseEntity.ok(service.listForUser(userId));
    }

    // PUBLIC_INTERFACE
    @PatchMapping("/{id}/read")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Mark notification read/unread", description = "Toggle read status of a notification.")
    public ResponseEntity<Notification> markRead(@PathVariable String id, @RequestParam boolean read) {
        return ResponseEntity.ok(service.markRead(id, read));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete notification", description = "Delete a notification (admin).")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
