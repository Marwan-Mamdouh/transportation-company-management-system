package com.travelSafe.buses.employee.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.domain.department.model.Department;
import com.travelSafe.buses.domain.department.service.GetDepartmentService;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeesByDepartmentIdService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetEmployeesByDepartmentIdServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;
  @Mock
  private GetDepartmentService getDepartmentService;

  @InjectMocks
  private GetEmployeesByDepartmentIdService getEmployeesByDepartmentIdService;

//  @BeforeEach
//  protected void setup() {
//    openMocks(this);
//  }

  @Test
  public void gavin_Employees_exist_in_department_when_get_employee_by_department_called_return_employee_list() {

    final Integer departmentId = 19;
    final Department department1 = new Department();
    department1.setId(departmentId);
    final Department department2 = new Department();
    department2.setId(90);
    // gavin
    final Employee employee1 = new Employee(36876543218906L, "test4First", "testLast2",
        "test1@gamil.com", "01142723335", LocalDate.parse("2018-09-15"), null, "123", null,
        department1);
    final Employee employee2 = new Employee(31876543218906L, "test1First", "testLast1",
        "test2@gamil.com", "01142705335", LocalDate.parse("2011-09-15"), null, "123", null,
        department1);
    final Employee employee3 = new Employee(32876543218906L, "test0First", "testLast3",
        "test3@gamil.com", "01142709335", LocalDate.parse("2010-09-15"), null, "123", null,
        department2);
    final List<Employee> employeesInDepartment19 = List.of(employee1, employee2);

    // when
    when(employeeRepository.findByDepartmentId(departmentId)).thenReturn(employeesInDepartment19);

    final List<Employee> response = getEmployeesByDepartmentIdService.execute(departmentId);

    // then
    assertEquals(employeesInDepartment19, response);
    verify(employeeRepository, times(1)).findByDepartmentId(departmentId);
  }

  @Test
  public void gavin_employees_does_not_exist_when_count_employee_called_return_empty_list() {
    // gavin
    final Integer departmentId = 25;
    // when
    when(employeeRepository.findByDepartmentId(departmentId)).thenReturn(List.of());
    final List<Employee> response = getEmployeesByDepartmentIdService.execute(departmentId);

    // then
    assertEquals(List.of(), response);
    verify(employeeRepository, times(1)).findByDepartmentId(departmentId);
  }
}