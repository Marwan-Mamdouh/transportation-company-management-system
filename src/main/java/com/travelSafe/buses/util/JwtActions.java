package com.travelSafe.buses.util;

import com.travelSafe.buses.configuration.security.JwtConfig;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtActions {

  private final JwtConfig jwtConfig;

  @Value("${jwt.expiration:600}") // 10 minutes
  private Integer jwtExpirationTime;

  public JwtActions(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  public String jwtCreate(String email, String... roles) {
    final var now = Instant.now();

    final var claims = JwtClaimsSet.builder().issuer("login_app").subject(email).issuedAt(now)
        .expiresAt(now.plusSeconds(jwtExpirationTime)).claim("scope", roles).build();
    return jwtConfig.jwtEncoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}