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

  private final JwtActions jwtActions;
  private final EmployeeRepository employeeRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private static final Logger logger = LoggerFactory.getLogger(EmployeeLoginService.class);

  public EmployeeLoginService(BCryptPasswordEncoder passwordEncoder,
      EmployeeRepository employeeRepository, JwtActions jwtActions) {
    this.passwordEncoder = passwordEncoder;
    this.employeeRepository = employeeRepository;
    this.jwtActions = jwtActions;
  }

  @Override
  public String execute(EmployeeLoginDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    final var employee = employeeRepository.findByEmail(input.email())
        .orElseThrow(EmailNotFoundException::new);

    if (!verifyPassword(input.password(), employee.getPassword())) {
      logger.error("Invalid password for email: {}", input.email());
      throw new NotValidPasswordException();
    }
    return jwtActions.jwtCreate(employee.getEmail(), employee.getRole());
  }

  private boolean verifyPassword(String password, String passwordHash) {
    logger.info("Verifying password:");
    return passwordEncoder.matches(password, passwordHash);
  }
}