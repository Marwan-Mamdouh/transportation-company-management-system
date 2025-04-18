package com.travelsave.buses.department.services;

import com.travelsave.buses.Command;
import com.travelsave.buses.department.DepartmentRepository;
import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.department.model.DepartmentDTO;
import com.travelsave.buses.department.model.UpdatedDepartmentCommand;
import com.travelsave.buses.exceptions.department.DepartmentNotFoundException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateDepartmentService implements Command<UpdatedDepartmentCommand, DepartmentDTO> {

  private final DepartmentRepository departmentRepository;

  public UpdateDepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public ResponseEntity<DepartmentDTO> execute(UpdatedDepartmentCommand updatedDepartment) {
    Optional<Department> optionalDepartment = departmentRepository.findById(updatedDepartment.id());
    if (optionalDepartment.isPresent()) {
      Department savedDepartment = departmentRepository.save(updatedDepartment.newDepartment());
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(new DepartmentDTO(savedDepartment));
    }
    throw new DepartmentNotFoundException();
  }
}
