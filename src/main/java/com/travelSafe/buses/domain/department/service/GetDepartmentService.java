package com.travelSafe.buses.domin.department.service;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.domin.department.DepartmentRepository;
import com.travelSafe.buses.domin.department.model.Department;
import com.travelSafe.buses.exceptions.department.DepartmentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetDepartmentService implements Query<Integer, Department> {

  private static final Logger logger = LoggerFactory.getLogger(GetDepartmentService.class);
  private final DepartmentRepository departmentRepository;

  public GetDepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public Department execute(Integer input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return departmentRepository.findById(input).orElseThrow(DepartmentNotFoundException::new);
  }
}