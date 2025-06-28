package com.travel.safe.buses.employee.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.employee.EmployeeMapper;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.EmployeeRequestDTO;
import com.travel.safe.buses.domain.employee.enums.Role;
import com.travel.safe.buses.domain.employee.exceptions.EmployeeNotFoundException;
import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeeService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetEmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;
  @Mock
  private EmployeeMapper mapper;

  @InjectMocks
  private GetEmployeeService getEmployeeService;

  @Test
  void givenEmployeeExists_whenGetEmployee_returnDto() {

    // given
    final Long empId = 90876543218906L;
    final Employee employee = new Employee(90876543218906L, "testFirst", "testLast",
        "test@gamil.com", "01142703335", LocalDate.parse("2012-09-15"), null, "123", Role.CLIENT,
        null, new Department());
    final var input = new EmployeeRequestDTO(empId, false);

    // when
    when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));
    final var response = getEmployeeService.execute(input);

    // then
    assertEquals(mapper.responseDTOFromEmployee(employee), response);
    verify(employeeRepository, times(1)).findById(empId);
  }

  @Test
  void givenEmployeeDoesNotExists_whenGetEmployeeCalled_throwEmployeeNotFoundException() {

    // given
    final Long empId = 90876543218206L;
    final var input = new EmployeeRequestDTO(empId, false);

    // when
    when(employeeRepository.findById(empId)).thenReturn(Optional.empty());

    // then
    assertThrows(EmployeeNotFoundException.class, () -> getEmployeeService.execute(input));
    verify(employeeRepository, times(1)).findById(empId);
  }
}