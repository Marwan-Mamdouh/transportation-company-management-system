package com.travelsave.buses.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.employee.services.get.GetEmployeeService;
import com.travelsave.buses.exceptions.employee.EmployeeNotFoundException;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetEmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private GetEmployeeService getEmployeeService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin_Employee_exists_when_get_employee_called_return_dto() {

    final String empId = "90876543218906";
    // gavin
    final Employee employee = new Employee("test", "test", "test@gamil.com", "01142703335",
        LocalDate.parse("2012-09-15"), empId, null, new Department());

    // when
    when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));
    final ResponseEntity<EmployeeDTO> response = getEmployeeService.execute(empId);

    // then
    assertEquals(ResponseEntity.ok(new EmployeeDTO(employee)), response);
    verify(employeeRepository, times(1)).findById(empId);
  }

  @Test
  public void gavin_employee_does_not_exists_when_get_employee_called_throw_employee_not_found_exception() {

    // gavin
    final String empId = "90876543218206";

    // when
    when(employeeRepository.findById(empId)).thenReturn(Optional.empty());

    // then
    assertThrows(EmployeeNotFoundException.class, () -> getEmployeeService.execute(empId));
    verify(employeeRepository, times(1)).findById(empId);
  }
}