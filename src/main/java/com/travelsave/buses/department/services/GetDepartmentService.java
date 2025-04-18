package com.travelsave.buses.department.services;

import com.travelsave.buses.Query;
import com.travelsave.buses.department.DepartmentRepository;
import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.department.model.DepartmentDTO;
import com.travelsave.buses.exceptions.department.DepartmentNotFoundException;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetDepartmentService implements Query<Integer, DepartmentDTO> {

  private final DepartmentRepository departmentRepository;

  public GetDepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public ResponseEntity<DepartmentDTO> execute(Integer id) {
    Optional<Department> optionalDepartment = departmentRepository.findById(id);
    if (optionalDepartment.isPresent()) {
      return ResponseEntity.ok(new DepartmentDTO(optionalDepartment.get()));
    }
    throw new DepartmentNotFoundException();
  }
}
