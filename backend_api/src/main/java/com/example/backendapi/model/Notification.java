package com.example.backendapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * PUBLIC_INTERFACE
 * In-app notification entity for users.
 */
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    private String userId;
    private String type; // INFO, ALERT, WARNING
    private String message;
    private boolean read = false;
    private Instant createdAt = Instant.now();

    public Notification() {}

    public Notification(String userId, String type, String message) {
        this.userId = userId;
        this.type = type;
        this.message = message;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getType() { return type; }
    public String getMessage() { return message; }
    public boolean isRead() { return read; }
    public Instant getCreatedAt() { return createdAt; }

    public void setId(String id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setType(String type) { this.type = type; }
    public void setMessage(String message) { this.message = message; }
    public void setRead(boolean read) { this.read = read; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
