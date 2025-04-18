package com.travelsave.buses.employee.services.get;

import com.travelsave.buses.Query;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.exceptions.department.DepartmentNotFoundException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeByDepartmentService implements Query<Integer, List<EmployeeDTO>> {

  private final EmployeeRepository employeeRepository;

  public GetEmployeeByDepartmentService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public ResponseEntity<List<EmployeeDTO>> execute(Integer input) {
    if (employeeRepository.existsByDepartment_Id(input)) {
      final List<Employee> employees = employeeRepository.findByDepartment_IdOrderByEmployeeIdAsc(
          input);
      final List<EmployeeDTO> employeeDTOS = employees.stream().map(EmployeeDTO::new).toList();
      return ResponseEntity.ok(employeeDTOS);
    }
    throw new DepartmentNotFoundException();
  }
}