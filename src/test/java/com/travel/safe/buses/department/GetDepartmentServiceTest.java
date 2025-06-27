package com.travel.safe.buses.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.department.DepartmentMapper;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.exception.DepartmentNotFoundException;
import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.department.service.GetDepartmentService;
import com.travel.safe.buses.domain.employee.model.Employee;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDepartmentServiceTest {

  @Mock
  private DepartmentRepository departmentRepository;

  @Mock
  private DepartmentMapper mapper;

  @InjectMocks
  private GetDepartmentService getDepartmentService;

  @Test
  void givenDepartmentExists_whenGetSearchDepartmentService_returnDto() {
    // given
    final int wantedId = 1;
    final Employee employee = new Employee();
    employee.setFirstname("Mohamed");

    final Department department = new Department(wantedId, "tick booker", 9000, 14.0, employee);

    // when
    when(departmentRepository.findById(wantedId)).thenReturn(Optional.of(department));
    final var response = getDepartmentService.execute(wantedId);

    // then
    assertEquals(mapper.entityToDto(department), response);
    verify(departmentRepository, times(1)).findById(wantedId);
  }

  @Test
  void givenDepartmentDoesNotExists_whenGetSearchDepartmentByIdService_throwDepartmentNotFoundException() {
    // given
    final int wantedId = 10;

    // when
    when(departmentRepository.findById(wantedId)).thenReturn(Optional.empty());

    // then
    assertThrows(DepartmentNotFoundException.class, () -> getDepartmentService.execute(wantedId));
    verify(departmentRepository, times(1)).findById(wantedId);
  }
}