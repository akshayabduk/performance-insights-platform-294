package com.example.backendapi.repository;

import com.example.backendapi.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Repository for notifications.
 */
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUserIdOrderByCreatedAtDesc(String userId);
}
