package com.travelSafe.buses.domain.employee.services;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.domain.employee.model.DTO.EmployeeLoginDTO;
import com.travelSafe.buses.configuration.security.jwt.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLoginService implements Query<EmployeeLoginDTO, String> {

  private final AuthenticationManager manager;
  private static final Logger logger = LoggerFactory.getLogger(EmployeeLoginService.class);

  public EmployeeLoginService(AuthenticationManager manager) {
    this.manager = manager;
  }

  @Override
  public String execute(EmployeeLoginDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    // Create an authentication token using the provided username and password
    // and authenticate it using the AuthenticationManager.
    // This will throw an exception if the authentication fails.
    final var token = new UsernamePasswordAuthenticationToken(input.email(), input.password());
    logger.info("making user token: {} with input: {}", token, input);

    // If authentication is successful, you can proceed with generating a JWT token
    // or any other logic you need to perform after successful login.
    logger.info("before auth with input: {}", token);
    final Authentication auth = manager.authenticate(token);
    logger.info("before auth context holder: {} with input: {}", auth, token);
    SecurityContextHolder.getContext().setAuthentication(auth);
    logger.info("after auth: {} with input: {}", auth, token);
    return JwtUtil.generateToken((User) auth.getPrincipal());
  }
}