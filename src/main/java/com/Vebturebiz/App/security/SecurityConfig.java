package com.Vebturebiz.App.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 6 configuration:
 * - Uses requestMatchers (antMatchers removed in v6).
 * - Stateless sessions with JWT filter.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  private final JwtAuthenticationFilter jwtFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable()) // CSRF disabled for stateless REST/JWT
      .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll() // public login
        .requestMatchers("/dashboard/hr/**", "/employees/**").hasRole("HR")
        .requestMatchers("/dashboard/employee/**", "/dashboard/employee").hasRole("EMPLOYEE")
        // Future roles (uncomment as endpoints are added):
        // .requestMatchers("/dashboard/manager/**").hasRole("MANAGER")
        // .requestMatchers("/dashboard/admin/**").hasRole("ADMIN")
        // .requestMatchers("/dashboard/teamlead/**").hasRole("TEAMLEAD")
        // .requestMatchers("/dashboard/intern/**").hasRole("INTERN")
        // .requestMatchers("/dashboard/supervisor/**").hasRole("SUPERVISOR")
        // .requestMatchers("/dashboard/hrassistant/**").hasRole("HRASSISTANT")
        // .requestMatchers("/dashboard/recruiter/**").hasRole("RECRUITER")
        // .requestMatchers("/dashboard/finance/**").hasRole("FINANCE")
        // .requestMatchers("/dashboard/cto/**").hasRole("CTO")
        // .requestMatchers("/dashboard/ceo/**").hasRole("CEO")
        .anyRequest().authenticated()
      );
    // Ensure JWT filter runs before username/password auth
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
    return cfg.getAuthenticationManager();
  }
}
