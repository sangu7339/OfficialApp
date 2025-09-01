package com.Vebturebiz.App.controller;

import com.Vebturebiz.App.dto.*;
import com.Vebturebiz.App.service.AuthService;
import com.Vebturebiz.App.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Public login endpoint that returns a JWT upon successful authentication. */
@RestController @RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  private final UserService userService;

  @PostMapping("/authenticate")
  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
    var user = userService.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
    return ResponseEntity.ok(authService.authenticate(request, user.getRole().name()));
  }
}
