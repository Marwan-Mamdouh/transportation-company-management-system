package com.travelsave.buses.employee.services.get;

import com.travelsave.buses.Query;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeesService implements Query<Void, List<EmployeeDTO>> {

  private final EmployeeRepository employeeRepository;

  public GetEmployeesService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public ResponseEntity<List<EmployeeDTO>> execute(Void input) {
    List<Employee> employees = employeeRepository.findAll();
    List<EmployeeDTO> employeeDTOS = employees.stream().map(EmployeeDTO::new).toList();
    return ResponseEntity.ok(employeeDTOS);
  }
}
