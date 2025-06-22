package com.travel.safe.buses.comman.configuration.security;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasAnyScope;
import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtConfig jwtConfig;

  public SecurityConfig(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> {
          auth.requestMatchers("/actuator/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
              "/webjars/**", "/api/employees/login", "/api/employees/register").permitAll();
          auth.requestMatchers("/api/departments/**", "/api/employees/**", "/api/seats/book",
                  "/api/travel-line/**", "/api/trip", "/api/trip/{tripId}", "/api/vehicles/**")
              .access(hasScope("ADMIN"));
          auth.requestMatchers("/api/seats/free/{tripId}").access(hasAnyScope("CLIENT", "ADMIN"));
          auth.anyRequest().authenticated();
        }).oauth2ResourceServer(config -> config.jwt(jwt -> jwt.decoder(jwtConfig.jwtDecoder())))
        .build();
  }
}