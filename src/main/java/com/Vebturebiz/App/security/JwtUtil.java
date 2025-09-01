package com.Vebturebiz.App.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Generates and validates HS256 JWT tokens.
 * Requires security.jwt.secret and security.jwt.expiration-ms properties.
 */
@Component
public class JwtUtil {

  private final Key key;
  private final long expirationMs;

  public JwtUtil(@Value("${security.jwt.secret}") String secret,
                 @Value("${security.jwt.expiration-ms}") long expirationMs) {
    // Secret must be at least 32 bytes for HS256
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
    this.expirationMs = expirationMs;
  }

  public String generateToken(String username, String role) {
    Date now = new Date();
    return Jwts.builder()
      .setSubject(username)
      .claim("role", role)
      .setIssuedAt(now)
      .setExpiration(new Date(now.getTime() + expirationMs))
      .signWith(key, SignatureAlgorithm.HS256)
      .compact();
  }

  public String extractUsername(String token) {
    return parse(token).getBody().getSubject();
  }

  public String extractRole(String token) {
    Object r = parse(token).getBody().get("role");
    return r == null ? null : r.toString();
  }

  public boolean validate(String token) {
    try { parse(token); return true; }
    catch (JwtException | IllegalArgumentException e) { return false; }
  }

  private Jws<Claims> parse(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }
}
