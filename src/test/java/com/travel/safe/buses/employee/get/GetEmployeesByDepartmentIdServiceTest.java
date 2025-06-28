package com.travel.safe.buses.employee.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.employee.EmployeeMapper;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.EmployeesGroupedRequestDTO;
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
class GetEmployeesByDepartmentIdServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;
  @Mock
  private EmployeeMapper mapper;

  @InjectMocks
  private GetEmployeesBy getEmployeesBy;

  @Test
  void givenEmployeesExistInDepartment_whenGetEmployeeByDepartmentCalled_returnEmployeeList() {

    // given
    final Integer departmentId = 19;
    final Long supervisorId = 15L;
    final Department department1 = new Department();
    final Department department2 = new Department();
    department1.setId(departmentId);
    department2.setId(90);
    final Employee employee1 = new Employee(36876543218906L, "test4First", "testLast2",
        "test1@gamil.com", "01142723335", LocalDate.parse("2018-09-15"), null, "123", Role.CLIENT,
        null, department1);
    final Employee employee2 = new Employee(31876543218906L, "test1First", "testLast1",
        "test2@gamil.com", "01142705335", LocalDate.parse("2011-09-15"), null, "123", Role.CLIENT,
        null, department1);
    final List<Employee> employeesInDepartment19 = List.of(employee1, employee2);
    final var employeeDto = employeesInDepartment19.stream().map(mapper::responseDTOFromEmployee)
        .toList();

    // when
    when(employeeRepository.findAll(any(Specification.class))).thenReturn(
        List.of(employee1, employee2));
    final var response = getEmployeesBy.execute(
        new EmployeesGroupedRequestDTO(supervisorId, departmentId, false));

    // then
    assertEquals(employeeDto, response);
    verify(employeeRepository, times(1)).findAll(any(Specification.class));
  }

  @Test
  void givenEmployeesDoesNotExist_whenGetEmployeeByDepartment_returnEmptyList() {
    // given
    final Integer departmentId = 25;
    final Long supervisorId = 30876543218906L;
    // when
    when(employeeRepository.findAll(any(Specification.class))).thenReturn(List.of());
    final var response = getEmployeesBy.execute(
        new EmployeesGroupedRequestDTO(supervisorId, departmentId, false));

    // then
    assertEquals(List.of(), response);
    verify(employeeRepository, times(1)).findAll(any(Specification.class));
  }
}