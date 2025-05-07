package com.travelSafe.buses.department.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.department.DepartmentRepository;
import com.travelSafe.buses.department.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteDepartmentService implements Command<Integer, Void> {

  private static final Logger logger = LoggerFactory.getLogger(DeleteDepartmentService.class);
  private final DepartmentRepository departmentRepository;
  private final GetDepartmentService getDepartmentService;

  public DeleteDepartmentService(DepartmentRepository departmentRepository,
      GetDepartmentService getDepartmentService) {
    this.departmentRepository = departmentRepository;
    this.getDepartmentService = getDepartmentService;
  }

  @Override
  public Void execute(Integer input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    final Department department = getDepartmentService.execute(input);
    departmentRepository.delete(department);
    return null;
  }
}