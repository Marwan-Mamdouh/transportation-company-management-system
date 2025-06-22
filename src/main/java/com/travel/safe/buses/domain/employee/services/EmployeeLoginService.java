package com.travel.safe.buses.domain.employee.services;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.comman.util.JwtActions;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.EmployeeLoginDTO;
import com.travel.safe.buses.domain.employee.exceptions.NotValidCredentialsException;
import com.travel.safe.buses.domain.employee.model.EmployeeAuth;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLoginService implements Query<EmployeeLoginDTO, String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeLoginService.class);
  private final PasswordEncoder passwordEncoder;
  private final EmployeeRepository employeeRepository;
  private final JwtActions jwtActions;

  public EmployeeLoginService(PasswordEncoder passwordEncoder, JwtActions jwtActions,
      EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtActions = jwtActions;
  }

  @Override
  public String execute(EmployeeLoginDTO input) {
    LOGGER.debug("Executing: {} with input: {}", getClass(), input);
    LOGGER.debug("Verifying existing email: {}", input.email());
    final Optional<EmployeeAuth> employee = employeeRepository.findByEmail(input.email());
    if (employee.isPresent()) {

      LOGGER.debug("Verifying password for email: {}", input.email());
      if (verifyPassword(input.password(), employee.get().getPassword())) {

        LOGGER.debug("Password verified for email: {}", input.email());
        return jwtActions.jwtCreate(employee.get().getEmail(), employee.get().getRole().toString());
      }
    }
    LOGGER.debug("Invalid password for email: {}", input.email());
    throw new NotValidCredentialsException();
  }

  private boolean verifyPassword(String password, String hashedPassword) {
    return passwordEncoder.matches(password, hashedPassword);
  }
}