package com.travelSafe.buses.employee.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.domain.department.model.Department;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.enums.Role;
import com.travelSafe.buses.domain.employee.services.get.CountEmployeesService;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CountEmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private CountEmployeesService countEmployeesService;

  @Test
  public void gavin_Employees_exist_when_count_employee_called_return_employee_count() {

    final Department department = new Department();
    final Long employeeCount = 3L;
    // gavin
    final Employee employee1 = new Employee(36876543218906L, "test4First", "testLast2",
        "test1@gamil.com", "01142723335", LocalDate.parse("2018-09-15"), null, "234", Role.CLIENT,
        null, department);
    final Employee employee2 = new Employee(31876543218906L, "test1First", "testLast1",
        "test2@gamil.com", "01142705335", LocalDate.parse("2011-09-15"), null, "333", Role.CLIENT,
        null, department);
    final Employee employee3 = new Employee(32876543218906L, "test0First", "testLast3",
        "test3@gamil.com", "01142709335", LocalDate.parse("2010-09-15"), null, "333", Role.CLIENT,
        null, department);

    // when
    when(employeeRepository.count()).thenReturn(employeeCount);
    final Long response = countEmployeesService.execute(null);

    // then
    assertEquals(employeeCount, response);
    verify(employeeRepository, times(1)).count();
  }

  @Test
  public void gavin_employees_does_not_exist_when_count_employee_called_return_zero() {

    // gavin & when
    when(employeeRepository.count()).thenReturn(0L);
    final Long response = countEmployeesService.execute(null);

    // then
    assertEquals(0L, response);
    verify(employeeRepository, times(1)).count();
  }
}