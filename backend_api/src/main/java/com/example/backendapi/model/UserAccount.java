package com.example.backendapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * PUBLIC_INTERFACE
 * User account entity for authentication and authorization.
 */
@Document(collection = "users")
public class UserAccount {

    @Id
    private String id;

    private String email;
    private String password;
    private String fullName;
    private Set<Role> roles = new HashSet<>();
    private Instant createdAt = Instant.now();

    public UserAccount() {}

    public UserAccount(String email, String password, String fullName, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.roles = roles != null ? roles : new HashSet<>();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public Set<Role> getRoles() { return roles; }
    public Instant getCreatedAt() { return createdAt; }

    public void setId(String id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
