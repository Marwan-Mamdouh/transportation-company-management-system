package com.travelSafe.buses.employee.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.domain.department.model.Department;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.model.enums.Role;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeeService;
import com.travelSafe.buses.exceptions.employee.EmployeeNotFoundException;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetEmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private GetEmployeeService getEmployeeService;

  @Test
  public void gavin_Employee_exists_when_get_employee_called_return_dto() {

    // gavin
    final Long empId = 90876543218906L;
    final Employee employee = new Employee(90876543218906L, "testFirst", "testLast",
        "test@gamil.com", "01142703335", LocalDate.parse("2012-09-15"), null, "123", Role.CLIENT ,null,
        new Department());

    // when
    when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));
    final Employee response = getEmployeeService.execute(empId);

    // then
    assertEquals(employee, response);
    verify(employeeRepository, times(1)).findById(empId);
  }

  @Test
  public void gavin_employee_does_not_exists_when_get_employee_called_throw_employee_not_found_exception() {

    // gavin
    final Long empId = 90876543218206L;

    // when
    when(employeeRepository.findById(empId)).thenReturn(Optional.empty());

    // then
    assertThrows(EmployeeNotFoundException.class, () -> getEmployeeService.execute(empId));
    verify(employeeRepository, times(1)).findById(empId);
  }
}