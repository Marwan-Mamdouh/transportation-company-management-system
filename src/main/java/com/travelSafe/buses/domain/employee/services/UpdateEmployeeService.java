package com.travelSafe.buses.domain.employee.services;

import com.travelSafe.buses.comman.shared.Command;
import com.travelSafe.buses.domain.employee.EmployeeMapper;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.dto.InputEmployeeDTO;
import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeService implements Command<InputEmployeeDTO, Employee> {

  private static final Logger logger = LoggerFactory.getLogger(UpdateEmployeeService.class);
  private final EmployeeRepository employeeRepository;
  private final GetEmployeeService getEmployeeService;
  private final EmployeeMapper employeeMapper;

  public UpdateEmployeeService(EmployeeRepository employeeRepository,
      GetEmployeeService getEmployeeService, EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.getEmployeeService = getEmployeeService;
    this.employeeMapper = employeeMapper;
  }

  @Override
  @Transactional
  public Employee execute(InputEmployeeDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    // check & validate Employee before save it to db
    final Long id = input.ssn();
    getEmployeeService.execute(id);
    // save & return
    return employeeRepository.save(employeeMapper.employeeFromInputDTO(input));
  }
}