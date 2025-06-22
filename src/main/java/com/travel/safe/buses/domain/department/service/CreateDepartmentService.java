package com.travel.safe.buses.domain.department.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.department.DTO.CreateDepartmentDto;
import com.travel.safe.buses.domain.department.DepartmentMapper;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.model.Department;
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
    logger.debug("Executing: {} with input: {}", getClass(), input);
    return departmentRepository.save(departmentMapper.toEntity(input));
  }
}