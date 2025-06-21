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
          auth.requestMatchers("/actuator/**", "/api/auth/**", "/v3/api-docs/**", "/swagger-ui/**",
              "/swagger-ui.html", "/webjars/**").permitAll();
          auth.requestMatchers("/department/**", "/departments/**", "/employee/**", "/employees/**",
                  "/seats/book", "/travel-line/**", "/trip", "/trip/{tripId}", "/vehicle/**")
              .access(hasScope("ADMIN"));
          auth.requestMatchers("/seats/free/{tripId}").access(hasAnyScope("CLIENT", "ADMIN"));
          auth.anyRequest().authenticated();
        }).oauth2ResourceServer(config -> config.jwt(jwt -> jwt.decoder(jwtConfig.jwtDecoder())))
        .build();
  }
}