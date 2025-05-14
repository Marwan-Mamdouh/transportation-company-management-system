package com.travelSafe.buses.domin.employee.services;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.domin.employee.EmployeeRepository;
import com.travelSafe.buses.domin.employee.model.DTO.InputEmployeeDTO;
import com.travelSafe.buses.domin.employee.model.Employee;
import com.travelSafe.buses.domin.employee.model.EmployeeMapper;
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
    // check
    // validate the input before send it to db
    if (employeeRepository.existsBySsn(input.ssn())) {
      throw new DuplicateEmployeeIdException();
    } else if (employeeRepository.existsByEmail(input.email())) {
      throw new DuplicateEmployeeEmailException();
    } else if (employeeRepository.existsByPhoneNumber(input.phoneNumber())) {
      throw new DuplicateEmployeePhoneNumberException();
    } else {
      // encode the password before saving
      final Employee employee = employeeMapper.employeeFromInputDTO(input);
      employee.setPassword(encoder.encode(employee.getPassword()));
      // save and return
      return employeeRepository.save(employee);
    }
  }
}