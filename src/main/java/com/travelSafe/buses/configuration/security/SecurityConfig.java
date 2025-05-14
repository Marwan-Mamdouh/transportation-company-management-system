package com.travelSafe.buses.configuration.security;

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
          auth.requestMatchers("/actuator/**").permitAll();
          auth.requestMatchers("/api/auth/login").permitAll();
          auth.requestMatchers("/api/auth/register").permitAll();
//          auth.requestMatchers("/employee/**").permitAll();
          auth.anyRequest().authenticated();
        }).oauth2ResourceServer(config -> config.jwt(jwt -> jwt.decoder(jwtConfig.jwtDecoder())))
        .build();
  }

//  @Bean
//  public JwtAuthenticationFilter jwtAuthenticationFilter() {
//    return new JwtAuthenticationFilter();
//  }
}