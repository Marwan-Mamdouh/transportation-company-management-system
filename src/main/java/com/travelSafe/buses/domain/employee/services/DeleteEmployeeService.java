package com.travelSafe.buses.domain.employee.services;

import com.travelSafe.buses.comman.shared.Command;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeService implements Command<Long, Void> {

  private static final Logger logger = LoggerFactory.getLogger(DeleteEmployeeService.class);
  private final EmployeeRepository employeeRepository;
  private final GetEmployeeService getEmployeeService;

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