package com.travelSafe.buses.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.department.model.Department;
import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.employee.services.get.GetEmployeesBySupervisorService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetEmployeesBySupervisorServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private GetEmployeesBySupervisorService getEmployeesBySupervisorService;

  private static List<Employee> getEmployees(Employee supervisor, Department department) {
    final Employee employee1 = new Employee(30876543218906L, "testFirst", "testLast",
        "test@gamil.com", "01142703335", null, LocalDate.parse("2012-09-15"), supervisor,
        department);
    final Employee employee2 = new Employee(23876543218906L, "testFirst", "testLast",
        "test@gamil.com", "01142703335", null, LocalDate.parse("2012-09-15"), supervisor,
        department);
    final Employee employee3 = new Employee(28876543218906L, "testFirst", "testLast",
        "test@gamil.com", "01142703335", null, LocalDate.parse("2012-09-15"), supervisor,
        department);
    return List.of(employee1, employee2, employee3);
  }

  @Test
  public void gavin_employees_exists_when_get_employees_by_supervisor_called_return_employees_list() {
    // gavin
    final Long supervisorId = 26876543218906L;
    final Department department = new Department();
    final Employee supervisor = new Employee(supervisorId, "testFirst", "testLast",
        "test@gamil.com", "01142703335", null, LocalDate.parse("2012-09-15"), null, department);
    final List<Employee> employees = getEmployees(supervisor, department);

    // when
    when(employeeRepository.findBySupervisor_EmployeeId(supervisorId)).thenReturn(employees);
    final List<Employee> response = getEmployeesBySupervisorService.execute(supervisorId);

    // then
    assertEquals(employees, response);
    verify(employeeRepository, times(1)).findBySupervisor_EmployeeId(supervisorId);
  }

  @Test
  public void gavin_employees_does_not_exists_when_get_employees_by_supervisor_called_return_empty_list() {
    // gavin
    final Long supervisorId = 26876543218906L;
    final Department department = new Department();
    final Employee supervisor = new Employee(supervisorId, "testFirst", "testLast",
        "test@gamil.com", "01142703335", null, LocalDate.parse("2012-09-15"), null, department);
    final List<Employee> employees = getEmployees(supervisor, department);

    // when
    when(employeeRepository.findBySupervisor_EmployeeId(30876543218906L)).thenReturn(List.of());
    final List<Employee> response = getEmployeesBySupervisorService.execute(30876543218906L);
    // then
    assertEquals(List.of(), response);
    verify(employeeRepository, times(1)).findBySupervisor_EmployeeId(30876543218906L);
  }
}