package com.travelSafe.buses.employee.services;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.employee.EmployeeRepository;
import com.travelSafe.buses.employee.model.DTO.InputEmployeeDTO;
import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.employee.model.EmployeeMapper;
import com.travelSafe.buses.exceptions.employee.DuplicateEmployeeEmailException;
import com.travelSafe.buses.exceptions.employee.DuplicateEmployeeIdException;
import com.travelSafe.buses.exceptions.employee.DuplicateEmployeePhoneNumberException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeService implements Command<InputEmployeeDTO, Employee> {

  private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeService.class);
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  public CreateEmployeeService(EmployeeRepository employeeRepository,
      EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
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
      // save and return
      return employeeRepository.save(employeeMapper.employeeFromInputDTO(input));
    }
  }
}