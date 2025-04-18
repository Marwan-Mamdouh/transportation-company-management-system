package com.travelsave.buses.employee.services.update;

import com.travelsave.buses.Command;
import com.travelsave.buses.department.DepartmentRepository;
import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.employee.model.UpdateEmployeeDepartmentCommand;
import com.travelsave.buses.exceptions.department.DepartmentNotFoundException;
import com.travelsave.buses.exceptions.employee.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeDepartmentService implements
    Command<UpdateEmployeeDepartmentCommand, EmployeeDTO> {

  private final EmployeeRepository employeeRepository;
  private final DepartmentRepository departmentRepository;

  public UpdateEmployeeDepartmentService(EmployeeRepository employeeRepository,
      DepartmentRepository departmentRepository) {
    this.employeeRepository = employeeRepository;
    this.departmentRepository = departmentRepository;
  }

  @Override
  @Transactional
  public ResponseEntity<EmployeeDTO> execute(UpdateEmployeeDepartmentCommand input) {
    final Employee employee = employeeRepository.findById(input.id())
        .orElseThrow(EmployeeNotFoundException::new);
    final Department department = departmentRepository.findById(input.departmentId())
        .orElseThrow(DepartmentNotFoundException::new);

    employee.setDepartment(department);
    final Employee savedEmployee = employeeRepository.save(employee);

    return ResponseEntity.ok(new EmployeeDTO(savedEmployee));
  }
}