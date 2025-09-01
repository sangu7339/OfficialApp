package com.Vebturebiz.App.service;

import com.Vebturebiz.App.entity.*;
import com.Vebturebiz.App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

/** Creates and fetches User records with proper password hashing. */
@Service @RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepo;
  private final PasswordEncoder encoder;

  public User createUser(String username, String email, String rawPassword, Role role) {
    User u = new User();
    u.setUsername(username);
    u.setEmail(email);
    u.setPassword(encoder.encode(rawPassword));
    u.setRole(role);
    return userRepo.save(u);
  }

  public Optional<User> findByUsername(String username) { return userRepo.findByUsername(username); }
}
