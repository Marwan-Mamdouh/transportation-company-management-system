package com.travelsave.buses.department.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelsave.buses.department.DepartmentRepository;
import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.department.model.DepartmentDTO;
import com.travelsave.buses.department.services.GetDepartmentsService;
import com.travelsave.buses.employee.model.Employee;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetDepartmentsServiceTest {

  @Mock
  private DepartmentRepository departmentRepository;

  @InjectMocks
  private GetDepartmentsService getDepartmentsService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin_departments_exists_when_get_departments_service_called_return_dto() {
    // gavin
    final int wantedId = 1;
    final Employee employee1 = new Employee();
    final Employee employee2 = new Employee();
    employee1.setFirstName("Ahmed");
    employee2.setFirstName("Hasan");

    final Department department1 = new Department(wantedId, "tick booker", 9000.0, 14.0, employee1);
    final Department department2 = new Department(wantedId, "bags", 7000.0, 10.0, employee2);
    List<Department> departments = List.of(department1, department2);

    // when
    when(departmentRepository.findAll()).thenReturn(departments);
    ResponseEntity<List<DepartmentDTO>> response = getDepartmentsService.execute(null);

    // then
    final List<DepartmentDTO> departmentDTOS = departments.stream().map(DepartmentDTO::new)
        .toList();
    assertEquals(ResponseEntity.ok(departmentDTOS), response);
    verify(departmentRepository, times(1)).findAll();
  }

  @Test
  public void gavin_departments_does_not_exists_when_get_customers_service_return_empty_list() {
    // gavin &when
    when(departmentRepository.findAll()).thenReturn(List.of());
    ResponseEntity<List<DepartmentDTO>> response = getDepartmentsService.execute(null);

    // then
    assertEquals(ResponseEntity.ok(List.of()), response);
    verify(departmentRepository, times(1)).findAll();
  }
}
