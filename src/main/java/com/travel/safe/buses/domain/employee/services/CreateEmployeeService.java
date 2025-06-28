package com.travel.safe.buses.domain.employee.services;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.employee.EmployeeMapper;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.CreateEmployeeDTO;
import com.travel.safe.buses.domain.employee.dto.EmployeeResponseDTO;
import com.travel.safe.buses.domain.employee.enums.Role;
import com.travel.safe.buses.domain.employee.exceptions.DuplicateEmployeeDataException;
import com.travel.safe.buses.domain.employee.model.Employee;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeService implements Command<CreateEmployeeDTO, EmployeeResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeService.class);
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper mapper;
  private final PasswordEncoder encoder;

  public CreateEmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
      PasswordEncoder encoder) {
    this.employeeRepository = employeeRepository;
    this.mapper = employeeMapper;
    this.encoder = encoder;
  }

  @Override
  @Transactional
  public EmployeeResponseDTO execute(CreateEmployeeDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    if (employeeRepository.existsBySsnOrPhoneNumberOrEmailIgnoreCase(input.ssn(),
        input.phoneNumber(), input.email())) {
      throw new DuplicateEmployeeDataException();
    }

    final Employee employee = hashPasswordAndSetRole(input);

    final Employee savedEmployee = employeeRepository.save(employee);
    logger.debug("Employee created with SSN: {} and,email: {}", savedEmployee.getSsn(),
        savedEmployee.getEmail());
    return mapper.responseDTOFromEmployee(savedEmployee);
  }

  private Employee hashPasswordAndSetRole(CreateEmployeeDTO input) {
    final Employee employee = mapper.employeeFromDto(input);
    employee.setPassword(encoder.encode(employee.getPassword()));
    employee.setRole(Role.CLIENT);
    return employee;
  }
}