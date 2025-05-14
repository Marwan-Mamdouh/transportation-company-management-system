package com.travelSafe.buses.configuration.security.jwt;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    // Extract the JWT token from the request
    final String authHeader = request.getHeader("Authorization");
    String token = null;
    // header: Authorization Bearer [jwtToken]
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);
    }
    // If the token is present, validate it and set the authentication in the
    // security context
    // Validate the token and set the authentication in the security context
    if (token != null && JwtUtil.isTokenValid(token)) {
      // Set authentication in the security context
      final Authentication authentication = new UsernamePasswordAuthenticationToken(
          JwtUtil.getClaims(token).getSubject(), null,
          // No credentials are needed for JWT authentication
          Collections.emptyList() // You can set the authorities here if needed
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    // Continue with the filter chain
    filterChain.doFilter(request, response);
  }
}