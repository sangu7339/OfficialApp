package com.Vebturebiz.App.security;

import com.Vebturebiz.App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;

/** Bridges our User entity to Spring Security's UserDetails with ROLE_* authority. */
@Service @RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var u = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new org.springframework.security.core.userdetails.User(
      u.getUsername(), u.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole().name()))
    );
  }
}
