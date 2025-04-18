package com.travelsave.buses.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.employee.services.get.GetEmployeesService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetEmployeesServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private GetEmployeesService getEmployeesService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin_employees_exists_when_get_employee_called_return_dto_list() {

    // gavin
    final Employee employee1 = new Employee("test1", "test1", "test1@gamil.com", "01142703335",
        LocalDate.parse("2012-09-15"), "90876543218906", null, new Department());
    final Employee employee2 = new Employee("test2", "test2", "test2@gmail.com", "01137829282",
        LocalDate.parse("2005-09-02"), "67890543210987", null, new Department());
    final List<Employee> employees = List.of(employee1, employee2);

    // when
    when(employeeRepository.findAll()).thenReturn(employees);
    final ResponseEntity<List<EmployeeDTO>> response = getEmployeesService.execute(null);
    final List<EmployeeDTO> employeeDTOS = employees.stream().map(EmployeeDTO::new).toList();

    // then
    assertEquals(ResponseEntity.status(HttpStatus.OK).body(employeeDTOS), response);
    verify(employeeRepository, times(1)).findAll();
  }

  @Test
  public void gavin_employee_does_not_exists_when_get_employee_called_return_empty_list() {

    // gavin & when
    when(employeeRepository.findAll()).thenReturn(List.of());
    ResponseEntity<List<EmployeeDTO>> response = getEmployeesService.execute(null);

    // then
    assertEquals(ResponseEntity.ok(List.of()), response);
    verify(employeeRepository, times(1)).findAll();
  }
}