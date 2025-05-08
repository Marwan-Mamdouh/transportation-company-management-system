package com.travelSafe.buses.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.department.model.Department;
import com.travelSafe.buses.department.service.GetDepartmentService;
import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.exceptions.department.DepartmentNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetDepartmentServiceTest {

  @Mock
  private DepartmentRepository departmentRepository;

  @InjectMocks
  private GetDepartmentService getDepartmentService;

  @Test
  public void gavin_department_exists_when_get_search_department_service_return_dto() {
    // gavin
    final int wantedId = 1;
    final Employee employee = new Employee();
    employee.setFirstName("Mohamed");

    final Department department = new Department(wantedId, "tick booker", 9000, 14.0, employee);

    // when
    when(departmentRepository.findById(wantedId)).thenReturn(Optional.of(department));
    final Department response = getDepartmentService.execute(wantedId);

    // then
    assertEquals(department, response);
    verify(departmentRepository, times(1)).findById(wantedId);
  }

  @Test
  public void gavin_department_does_not_exists_when_get_search_department_by_id_service_throw_department_not_found_exception() {
    // gavin
    final int wantedId = 10;

    // when
    when(departmentRepository.findById(wantedId)).thenReturn(Optional.empty());

    // then
    assertThrows(DepartmentNotFoundException.class, () -> getDepartmentService.execute(wantedId));
    verify(departmentRepository, times(1)).findById(wantedId);
  }
}