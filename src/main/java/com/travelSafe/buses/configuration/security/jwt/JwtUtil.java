package com.travelSafe.buses.configuration.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.User;

public class JwtUtil {

  public static String generateToken(User user) {
    return Jwts.builder().subject(user.getUsername())
        .expiration(new Date(System.currentTimeMillis() + 300_000)) // 5 minutes
        .signWith(getSigningKey()).compact();
  }

  public static Claims getClaims(String token) {
    return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
  }

  public static boolean isTokenValid(String token) {
    // can have more validations here
    return !isExpired(token);
  }

  private static boolean isExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }

  private static SecretKey getSigningKey() {
    final byte[] keyBytes = Decoders.BASE64.decode(
        "helloThisIsASecretKeyForMyAppToTestWithAndItIsVeryLong");
    return Keys.hmacShaKeyFor(keyBytes);
    // return Jwts.SIG.HS256.key().build();
  }
}