package com.example.backendapi.service;

import com.example.backendapi.dto.NotificationRequest;
import com.example.backendapi.model.Notification;
import com.example.backendapi.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * PUBLIC_INTERFACE
 * Service to manage user notifications.
 */
@Service
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    // PUBLIC_INTERFACE
    public Notification create(NotificationRequest req) {
        Notification n = new Notification(req.getUserId(), req.getType(), req.getMessage());
        return repo.save(n);
    }

    // PUBLIC_INTERFACE
    public List<Notification> listForUser(String userId) {
        return repo.findByUserIdOrderByCreatedAtDesc(userId);
    }

    // PUBLIC_INTERFACE
    public Notification markRead(String id, boolean read) {
        Notification n = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Notification not found"));
        n.setRead(read);
        return repo.save(n);
    }

    // PUBLIC_INTERFACE
    public void delete(String id) {
        repo.deleteById(id);
    }
}
