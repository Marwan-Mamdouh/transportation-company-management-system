package com.travel.safe.buses.domain.department.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.dto.DepartmentResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteDepartmentService implements Command<Integer, DepartmentResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(DeleteDepartmentService.class);
  private final DepartmentRepository departmentRepository;
  private final GetDepartmentService getDepartmentService;

  public DeleteDepartmentService(DepartmentRepository departmentRepository,
      GetDepartmentService getDepartmentService) {
    this.departmentRepository = departmentRepository;
    this.getDepartmentService = getDepartmentService;
  }

  @Override
  public DepartmentResponseDTO execute(Integer input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    final var departmentResponseDTO = getDepartmentService.execute(input);
    departmentRepository.deleteById(departmentResponseDTO.id());
    return departmentResponseDTO;
  }
}