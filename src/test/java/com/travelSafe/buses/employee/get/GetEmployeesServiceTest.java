package com.travelSafe.buses.employee.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.domain.department.model.Department;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.enums.Role;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeesService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetEmployeesServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private GetEmployeesService getEmployeesService;

  @Test
  public void gavin_employees_exists_when_get_employee_called_return_dto_list() {

    // gavin
    final Employee employee1 = new Employee(90876543218906L, "test1", "test1", "test1@gamil.com",
        "01142703335", LocalDate.parse("2012-09-15"), null, "123", Role.CLIENT, null,
        new Department());
    final Employee employee2 = new Employee(67890543210987L, "test2", "test2", "test2@gmail.com",
        "01137829282", LocalDate.parse("2005-09-02"), null, "123", Role.CLIENT, null,
        new Department());
    final List<Employee> employees = List.of(employee1, employee2);

    // when
    when(employeeRepository.findAll()).thenReturn(employees);
    final List<Employee> response = getEmployeesService.execute(null);

    // then
    assertEquals(employees, response);
    verify(employeeRepository, times(1)).findAll();
  }

  @Test
  public void gavin_employee_does_not_exists_when_get_employee_called_return_empty_list() {

    // gavin & when
    when(employeeRepository.findAll()).thenReturn(List.of());
    final List<Employee> response = getEmployeesService.execute(null);

    // then
    assertEquals(List.of(), response);
    verify(employeeRepository, times(1)).findAll();
  }
}