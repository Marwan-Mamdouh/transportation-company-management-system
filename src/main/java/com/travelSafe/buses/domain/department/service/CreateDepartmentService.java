package com.travelSafe.buses.domin.department.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.domin.department.DepartmentRepository;
import com.travelSafe.buses.domin.department.model.DTO.CreateDepartmentDto;
import com.travelSafe.buses.domin.department.model.Department;
import com.travelSafe.buses.domin.department.model.DepartmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateDepartmentService implements Command<CreateDepartmentDto, Department> {

  private static final Logger logger = LoggerFactory.getLogger(CreateDepartmentService.class);
  private final DepartmentRepository departmentRepository;
  private final DepartmentMapper departmentMapper;

  public CreateDepartmentService(DepartmentRepository departmentRepository,
      DepartmentMapper departmentMapper) {
    this.departmentRepository = departmentRepository;
    this.departmentMapper = departmentMapper;
  }

  @Override
  public Department execute(CreateDepartmentDto input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return departmentRepository.save(departmentMapper.toEntity(input));
  }
}