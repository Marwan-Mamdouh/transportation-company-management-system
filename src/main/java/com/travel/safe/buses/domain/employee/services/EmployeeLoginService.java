package com.travel.safe.buses.domain.employee.services;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.comman.util.JwtActions;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.EmployeeLoginDTO;
import com.travel.safe.buses.domain.employee.exceptions.EmailNotFoundException;
import com.travel.safe.buses.domain.employee.exceptions.NotValidPasswordException;
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
    LOGGER.debug("Executing: {} with input: {}", getClass(), input);
    final var employee = employeeRepository.findByEmail(input.email())
        .orElseThrow(EmailNotFoundException::new);

    LOGGER.debug("Verifying password for email: {}", input.email());
    if (!verifyPassword(input.password(), employee.getPassword())) {

      LOGGER.error("Invalid password for email: {}", input.email());
      throw new NotValidPasswordException();
    }

    LOGGER.debug("Password verified for email: {}", input.email());
    return jwtActions.jwtCreate(employee.getEmail(), employee.getRole().toString());
  }

  private boolean verifyPassword(String password, String passwordHash) {
    return passwordEncoder.matches(password, passwordHash);
  }
}