package com.travelsave.buses.department.services;

import com.travelsave.buses.Command;
import com.travelsave.buses.department.DepartmentRepository;
import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.department.model.DepartmentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateDepartmentService implements Command<Department, DepartmentDTO> {

  private final DepartmentRepository departmentRepository;

  public CreateDepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public ResponseEntity<DepartmentDTO> execute(Department department) {
    Department savedDepartment = departmentRepository.save(department);
    return ResponseEntity.status(HttpStatus.CREATED).body(new DepartmentDTO(savedDepartment));
  }
}
