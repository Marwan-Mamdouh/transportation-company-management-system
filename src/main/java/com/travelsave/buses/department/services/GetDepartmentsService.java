package com.travelsave.buses.department.services;

import com.travelsave.buses.Query;
import com.travelsave.buses.department.DepartmentRepository;
import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.department.model.DepartmentDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetDepartmentsService implements Query<Void, List<DepartmentDTO>> {

  private final DepartmentRepository departmentRepository;

  public GetDepartmentsService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public ResponseEntity<List<DepartmentDTO>> execute(Void input) {
    List<Department> departments = departmentRepository.findAll();
    List<DepartmentDTO> DepartmentDTOS = departments.stream().map(DepartmentDTO::new).toList();
    return ResponseEntity.ok(DepartmentDTOS);
  }
}
