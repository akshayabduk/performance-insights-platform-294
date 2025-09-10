package com.example.backendapi.repository;

import com.example.backendapi.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * PUBLIC_INTERFACE
 * Repository for user accounts.
 */
public interface UserRepository extends MongoRepository<UserAccount, String> {
    Optional<UserAccount> findByEmail(String email);
    boolean existsByEmail(String email);
}
