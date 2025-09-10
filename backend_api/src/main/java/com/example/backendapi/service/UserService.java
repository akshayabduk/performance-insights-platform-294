package com.example.backendapi.service;

import com.example.backendapi.dto.UpdateRoleRequest;
import com.example.backendapi.model.Role;
import com.example.backendapi.model.UserAccount;
import com.example.backendapi.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * PUBLIC_INTERFACE
 * User service implementing UserDetailsService for Spring Security.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    // PUBLIC_INTERFACE
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount ua = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var authorities = ua.getRoles().stream()
                .map(r -> "ROLE_" + (r == Role.ROLE_ADMIN ? "ADMIN" : "USER"))
                .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new User(ua.getEmail(), ua.getPassword(), authorities);
    }

    // PUBLIC_INTERFACE
    public Optional<UserAccount> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // PUBLIC_INTERFACE
    public UserAccount createUser(String email, String password, String fullName) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        UserAccount ua = new UserAccount(email, encoder.encode(password), fullName, new HashSet<>(Set.of(Role.ROLE_USER)));
        return userRepository.save(ua);
    }

    // PUBLIC_INTERFACE
    public List<UserAccount> listUsers() {
        return userRepository.findAll();
    }

    // PUBLIC_INTERFACE
    public UserAccount updateUserRoles(String id, UpdateRoleRequest req) {
        UserAccount ua = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        Set<Role> roles = new HashSet<>(ua.getRoles());
        if (Boolean.TRUE.equals(req.getAdmin())) {
            roles.add(Role.ROLE_ADMIN);
        } else {
            roles.remove(Role.ROLE_ADMIN);
            if (roles.isEmpty()) {
                roles.add(Role.ROLE_USER);
            }
        }
        ua.setRoles(roles);
        return userRepository.save(ua);
    }

    // PUBLIC_INTERFACE
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    // PUBLIC_INTERFACE
    public Set<String> getRoleNames(UserAccount ua) {
        return ua.getRoles().stream().map(r -> r.name()).collect(Collectors.toSet());
    }
}
