package com.travelsave.buses.department.services;

import com.travelsave.buses.Command;
import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.department.DepartmentRepository;
import com.travelsave.buses.exceptions.department.DepartmentNotFoundException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteDepartmentService implements Command<Integer, Void> {

  private final DepartmentRepository departmentRepository;

  public DeleteDepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public ResponseEntity<Void> execute(Integer id) {
    Optional<Department> departmentOptional = departmentRepository.findById(id);
    if (departmentOptional.isPresent()) {
      departmentRepository.delete(departmentOptional.get());
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    throw new DepartmentNotFoundException();
  }
}
