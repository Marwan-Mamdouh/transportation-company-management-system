package com.travel.safe.buses.domain.department.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.dto.DepartmentSearchDTO;
import com.travel.safe.buses.domain.department.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetDepartmentsService implements Query<DepartmentSearchDTO, Page<Department>> {

  private static final Logger logger = LoggerFactory.getLogger(GetDepartmentsService.class);
  private final DepartmentRepository departmentRepository;

  public GetDepartmentsService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public Page<Department> execute(DepartmentSearchDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    return departmentRepository.findByName(input.departmentName(), input.pageable());
  }
}