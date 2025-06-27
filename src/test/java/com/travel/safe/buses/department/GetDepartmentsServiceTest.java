package com.travel.safe.buses.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.department.DepartmentMapper;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.dto.DepartmentSearchDTO;
import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.department.service.GetDepartmentsService;
import com.travel.safe.buses.domain.employee.model.Employee;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@ExtendWith(MockitoExtension.class)
class GetDepartmentsServiceTest {

  @Mock
  private DepartmentMapper mapper;

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

    final Department department2 = new Department(wantedId, "bags", 7000, 10.0, employee2);
    final Page<Department> departments = new PageImpl<>(List.of(department2));

    final DepartmentSearchDTO input = new DepartmentSearchDTO("bags", null);

    // when
    when(departmentRepository.findByName(input.departmentName(), input.pageable())).thenReturn(
        departments);
    final Page<Department> response = getDepartmentsService.execute(input);

    // then
    assertEquals(departments, response);
    verify(departmentRepository, times(1)).findByName(input.departmentName(), input.pageable());
  }

  @Test
  void givenDepartmentsDoesNotExists_whenGetCustomersService_returnEmptyList() {
    // given & when

    Page<Department> emptyPage = Page.empty();
    final DepartmentSearchDTO input = new DepartmentSearchDTO("bags", null);
    when(departmentRepository.findByName(input.departmentName(), input.pageable())).thenReturn(
        emptyPage);
    final Page<Department> response = getDepartmentsService.execute(input);

    // then
    assertEquals(emptyPage, response);
    verify(departmentRepository, times(1)).findByName(input.departmentName(), input.pageable());
  }
}
