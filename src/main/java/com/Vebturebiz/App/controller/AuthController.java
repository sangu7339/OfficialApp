package com.Vebturebiz.App.controller;

import com.Vebturebiz.App.dto.AuthRequest;
import com.Vebturebiz.App.dto.AuthResponse;
import com.Vebturebiz.App.service.AuthService;
import com.Vebturebiz.App.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  private final UserService userService;

  @PostMapping("/authenticate")
  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
    try {
      // Resolve role after authentication succeeds, to avoid leaking username existence
      var user = userService.findByUsername(request.getUsername())
          .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
      var response = authService.authenticate(request, user.getRole().name());
      return ResponseEntity.ok(response);
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(401).build();
    }
  }
}
