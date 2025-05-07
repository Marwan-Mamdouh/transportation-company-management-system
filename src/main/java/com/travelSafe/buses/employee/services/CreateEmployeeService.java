package com.travelSafe.buses.employee.services;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.employee.EmployeeRepository;
import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.exceptions.employee.DuplicateEmployeeEmailException;
import com.travelSafe.buses.exceptions.employee.DuplicateEmployeeIdException;
import com.travelSafe.buses.exceptions.employee.DuplicateEmployeePhoneNumberException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeService implements Command<Employee, Employee> {

  private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeService.class);
  private final EmployeeRepository employeeRepository;

  public CreateEmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional
  public Employee execute(Employee input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    // check
    // validate the input before send it to db
    if (employeeRepository.existsBySsn(input.getSsn())) {
      throw new DuplicateEmployeeIdException();
    } else if (employeeRepository.existsByEmail(input.getEmail())) {
      throw new DuplicateEmployeeEmailException();
    } else if (employeeRepository.existsByPhoneNumber(input.getPhoneNumber())) {
      throw new DuplicateEmployeePhoneNumberException();
    } else {
      // save and return
      return employeeRepository.save(input);
    }
  }
}