package com.travelSafe.buses.employee.services;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.employee.EmployeeRepository;
import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.employee.model.DTO.UpdateEmployeeDTO;
import com.travelSafe.buses.employee.services.get.GetEmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeService implements Command<UpdateEmployeeDTO, Employee> {

  private final EmployeeRepository employeeRepository;
  private final GetEmployeeService getEmployeeService;

  private static final Logger logger = LoggerFactory.getLogger(UpdateEmployeeService.class);

  public UpdateEmployeeService(EmployeeRepository employeeRepository,
      GetEmployeeService getEmployeeService) {
    this.employeeRepository = employeeRepository;
    this.getEmployeeService = getEmployeeService;
  }

  @Override
  @Transactional
  public Employee execute(UpdateEmployeeDTO command) {
    logger.info("Executing: {} with input: {}", getClass(), command);
    // check & validate Employee before save it to db
    final Long id = command.id();
    getEmployeeService.execute(id);
    // save & return
    final Employee employee = command.updateedEmployee();
    employee.setSsn(id);
    return employeeRepository.save(employee);
  }
}