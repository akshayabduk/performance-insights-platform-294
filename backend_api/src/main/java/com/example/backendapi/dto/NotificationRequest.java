package com.example.backendapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * PUBLIC_INTERFACE
 * Request DTO to create a notification.
 */
public class NotificationRequest {

    @NotBlank
    @Schema(description = "Target user ID for the notification")
    private String userId;

    @NotBlank
    @Schema(description = "Notification type (INFO/ALERT/WARNING)")
    private String type;

    @NotBlank
    @Schema(description = "Notification message")
    private String message;

    public String getUserId() { return userId; }
    public String getType() { return type; }
    public String getMessage() { return message; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setType(String type) { this.type = type; }
    public void setMessage(String message) { this.message = message; }
}
