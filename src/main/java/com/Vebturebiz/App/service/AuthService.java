package com.Vebturebiz.App.service;

import com.Vebturebiz.App.security.JwtUtil;
import com.Vebturebiz.App.dto.AuthRequest;
import com.Vebturebiz.App.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/** Performs credential verification and returns a signed JWT on success. */
@Service @RequiredArgsConstructor
public class AuthService {
  private final AuthenticationManager authManager;
  private final JwtUtil jwtUtil;

  public AuthResponse authenticate(AuthRequest req, String role) {
    authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
    String token = jwtUtil.generateToken(req.getUsername(), role);
    return new AuthResponse(token);
  }
}
