package com.travelSafe.buses.domain.employee.services;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.model.EmployeeMapper;
import com.travelSafe.buses.domain.employee.model.dto.InputEmployeeDTO;
import com.travelSafe.buses.domain.employee.model.enums.Role;
import com.travelSafe.buses.exceptions.employee.DuplicateEmployeeEmailException;
import com.travelSafe.buses.exceptions.employee.DuplicateEmployeeIdException;
import com.travelSafe.buses.exceptions.employee.DuplicateEmployeePhoneNumberException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeService implements Command<InputEmployeeDTO, Employee> {

  private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeService.class);
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;
  private final PasswordEncoder encoder;

  public CreateEmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
      PasswordEncoder encoder) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
    this.encoder = encoder;
  }

  @Override
  @Transactional
  public Employee execute(InputEmployeeDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    logger.info("Starting employee creation for email: {}", input.email());
    // validate input
    validateInput(input);

    // encode the password before saving
    final Employee employee = employeeMapper.employeeFromInputDTO(input);
    employee.setPassword(encoder.encode(employee.getPassword()));
    employee.setRole(Role.CLIENT);
    // save and return
    final Employee savedEmployee = employeeRepository.save(employee);
    logger.info("Employee created with SSN: {} and,email: {}", savedEmployee.getSsn(),
        savedEmployee.getEmail());
    return savedEmployee;
  }

  private void validateInput(InputEmployeeDTO input) {
    // Implement validation logic here
    if (employeeRepository.existsBySsn(input.ssn())) {
      logger.error("Duplicate SSN found: {}", input.ssn());
      throw new DuplicateEmployeeIdException();
    }
    if (employeeRepository.existsByEmail(input.email())) {
      logger.error("Duplicate email found: {}", input.email());
      throw new DuplicateEmployeeEmailException();
    }
    if (employeeRepository.existsByPhoneNumber(input.phoneNumber())) {
      logger.error("Duplicate phone number found: {}", input.phoneNumber());
      throw new DuplicateEmployeePhoneNumberException();
    }
  }
}