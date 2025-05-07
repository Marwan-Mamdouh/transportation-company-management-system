package com.travelSafe.buses.employee.services;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.employee.EmployeeRepository;
import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.employee.services.get.GetEmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeService implements Command<Long, Void> {

  private final EmployeeRepository employeeRepository;
  private final GetEmployeeService getEmployeeService;

  private static final Logger logger = LoggerFactory.getLogger(DeleteEmployeeService.class);

  public DeleteEmployeeService(EmployeeRepository employeeRepository,
      GetEmployeeService getEmployeeService) {
    this.employeeRepository = employeeRepository;
    this.getEmployeeService = getEmployeeService;
  }

  @Override
  @Transactional
  public Void execute(Long input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    final Employee employee = getEmployeeService.execute(input);
    employeeRepository.delete(employee);
    return null;
  }
}