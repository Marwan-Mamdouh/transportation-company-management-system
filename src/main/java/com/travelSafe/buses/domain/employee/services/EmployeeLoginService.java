package com.travelSafe.buses.domain.employee.services;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.model.dto.EmployeeLoginDTO;
import com.travelSafe.buses.exceptions.employee.EmailNotFoundException;
import com.travelSafe.buses.exceptions.employee.NotValidPasswordException;
import com.travelSafe.buses.util.JwtActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLoginService implements Query<EmployeeLoginDTO, String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeLoginService.class);
  private final BCryptPasswordEncoder passwordEncoder;
  private final EmployeeRepository employeeRepository;
  private final JwtActions jwtActions;

  public EmployeeLoginService(BCryptPasswordEncoder passwordEncoder, JwtActions jwtActions,
      EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtActions = jwtActions;
  }

  @Override
  public String execute(EmployeeLoginDTO input) {
    LOGGER.info("Executing: {} with input: {}", getClass(), input);
    final var employee = employeeRepository.findByEmail(input.email())
        .orElseThrow(EmailNotFoundException::new);

    LOGGER.info("Verifying password for email: {}", input.email());
    if (!verifyPassword(input.password(), employee.getPassword())) {

      LOGGER.error("Invalid password for email: {}", input.email());
      throw new NotValidPasswordException();
    }

    LOGGER.info("Password verified for email: {}", input.email());
    return jwtActions.jwtCreate(employee.getEmail(), employee.getRole().toString());
  }

  private boolean verifyPassword(String password, String passwordHash) {
    return passwordEncoder.matches(password, passwordHash);
  }
}