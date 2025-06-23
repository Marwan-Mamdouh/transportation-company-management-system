package com.travel.safe.buses.employee.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.EmployeeSpecificationDTO;
import com.travel.safe.buses.domain.employee.enums.Role;
import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeesBy;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
class GetEmployeesBySupervisorServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private GetEmployeesBy getEmployeesBy;

  private static List<Employee> getEmployees(Employee supervisor, Department department) {
    return List.of(
        new Employee(30876543218906L, "testFirst", "testLast", "test@gamil.com", "01142703335",
            LocalDate.parse("2012-09-15"), null, "123", Role.CLIENT, supervisor, department),
        new Employee(23876543218906L, "testFirst", "testLast", "test@gamil.com", "01142703335",
            LocalDate.parse("2012-09-15"), null, "123", Role.CLIENT, supervisor, department),
        new Employee(28876543218906L, "testFirst", "testLast", "test@gamil.com", "01142703335",
            LocalDate.parse("2012-09-15"), null, "123", Role.CLIENT, supervisor, department));
  }

  @Test
  void givenEmployeesExists_whenGetEmployeesBySupervisor_returnEmployeesList() {
    // given
    final Integer departmentId = 19;
    final Long supervisorId = 26876543218906L;
    final Department department = new Department();
    final Employee supervisor = new Employee(supervisorId, "sup", "visor", "sup@visor.com",
        "0123456789", LocalDate.parse("2000-01-01"), null, "pass", Role.ADMIN, null, department);
    final List<Employee> employees = getEmployees(supervisor, department);

    when(employeeRepository.findAll(any(Specification.class))).thenReturn(employees);

    // when
    final List<Employee> response = getEmployeesBy.execute(
        new EmployeeSpecificationDTO(supervisorId, departmentId));

    // then
    assertEquals(employees, response);
    verify(employeeRepository, times(1)).findAll(any(Specification.class));
  }

  @Test
  void givenNoEmployees_whenGetEmployeesBySupervisor_returnEmptyList() {
    // given
    final Long supervisorId = 30876543218906L;
    final Integer departmentId = 19;
    when(employeeRepository.findAll(any(Specification.class))).thenReturn(List.of());

    // when
    List<Employee> response = getEmployeesBy.execute(
        new EmployeeSpecificationDTO(supervisorId, departmentId));

    // then
    assertEquals(List.of(), response);
    verify(employeeRepository, times(1)).findAll((any(Specification.class)));
  }
}