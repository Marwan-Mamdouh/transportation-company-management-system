package com.travelsave.buses.employee.services.get;

import com.travelsave.buses.Query;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeesBySupervisorService implements Query<String, List<EmployeeDTO>> {

  private final EmployeeRepository employeeRepository;

  public GetEmployeesBySupervisorService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public ResponseEntity<List<EmployeeDTO>> execute(String input) {
    if (employeeRepository.existsBySupervisor_EmployeeId(input)) {
      final List<Employee> employees = employeeRepository.findBySupervisor_EmployeeIdOrderByEmployeeIdAsc(
          input);
      final List<EmployeeDTO> employeeDTOS = employees.stream().map(EmployeeDTO::new).toList();
      return ResponseEntity.ok(employeeDTOS);
    }
    throw new RuntimeException("Supervisor Not found");
  }
}