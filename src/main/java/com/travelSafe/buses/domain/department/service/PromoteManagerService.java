package com.travelSafe.buses.domin.department.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.domin.department.DepartmentRepository;
import com.travelSafe.buses.domin.department.model.DTO.PromoteManagerDTO;
import com.travelSafe.buses.domin.department.model.Department;
import com.travelSafe.buses.domin.employee.model.Employee;
import com.travelSafe.buses.domin.employee.services.get.GetEmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PromoteManagerService implements Command<PromoteManagerDTO, Department> {

  private static final Logger logger = LoggerFactory.getLogger(PromoteManagerService.class);
  private final DepartmentRepository departmentRepository;
  private final GetEmployeeService getEmployeeService;
  private final GetDepartmentService getDepartmentService;

  public PromoteManagerService(DepartmentRepository departmentRepository,
      GetEmployeeService getEmployeeService, GetDepartmentService getDepartmentService) {
    this.departmentRepository = departmentRepository;
    this.getEmployeeService = getEmployeeService;
    this.getDepartmentService = getDepartmentService;
  }

  @Override
  @Transactional
  public Department execute(PromoteManagerDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    // check
    final Employee foundEmployee = getEmployeeService.execute(input.managerId());
    final Department foundDepartment = getDepartmentService.execute(input.departmentId());
    // save
    foundDepartment.setDepartmentManager(foundEmployee);
    // return
    return departmentRepository.save(foundDepartment);
  }
}