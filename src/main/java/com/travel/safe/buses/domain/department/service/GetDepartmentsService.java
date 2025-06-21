package com.travel.safe.buses.domain.department.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.model.Department;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetDepartmentsService implements Query<Void, List<Department>> {

  private static final Logger logger = LoggerFactory.getLogger(GetDepartmentsService.class);
  private final DepartmentRepository departmentRepository;

  public GetDepartmentsService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public List<Department> execute(Void input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return departmentRepository.findAll();
  }
}
