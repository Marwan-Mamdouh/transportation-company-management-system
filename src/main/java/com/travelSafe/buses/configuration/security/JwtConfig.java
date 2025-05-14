package com.travelSafe.buses.configuration.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

@Configuration
public class JwtConfig {

  @Value("${jwt.public-key-location}")
  private RSAPublicKey publicKey;

  @Value("${jwt.private-key-location}")
  private RSAPrivateKey privateKey;

  @Bean
  public JwtEncoder jwtEncoder() {
    final var jwt = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
    final var jwts = new ImmutableJWKSet<>(new JWKSet(jwt));
    return new NimbusJwtEncoder(jwts);
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
  }
}