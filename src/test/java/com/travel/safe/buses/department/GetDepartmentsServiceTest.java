package com.travel.safe.buses.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.department.service.GetDepartmentsService;
import com.travel.safe.buses.domain.employee.model.Employee;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDepartmentsServiceTest {

  @Mock
  private DepartmentRepository departmentRepository;

  @InjectMocks
  private GetDepartmentsService getDepartmentsService;

  @Test
  void givenDepartmentsExists_whenGetDepartmentsServiceCalled_returnDto() {
    // given
    final int wantedId = 1;
    final Employee employee1 = new Employee();
    final Employee employee2 = new Employee();
    employee1.setFirstname("Ahmed");
    employee2.setFirstname("Hasan");

    final Department department1 = new Department(wantedId, "tick booker", 9000, 14.0, employee1);
    final Department department2 = new Department(wantedId, "bags", 7000, 10.0, employee2);
    List<Department> departments = List.of(department1, department2);

    // when
    when(departmentRepository.findAll()).thenReturn(departments);
    List<Department> response = getDepartmentsService.execute(null);

    // then
    assertEquals(departments, response);
    verify(departmentRepository, times(1)).findAll();
  }

  @Test
  void givenDepartmentsDoesNotExists_whenGetCustomersService_returnEmptyList() {
    // given & when
    when(departmentRepository.findAll()).thenReturn(List.of());
    List<Department> response = getDepartmentsService.execute(null);

    // then
    assertEquals(List.of(), response);
    verify(departmentRepository, times(1)).findAll();
  }
}
