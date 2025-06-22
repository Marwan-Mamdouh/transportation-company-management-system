package com.travel.safe.buses.employee.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.services.get.CountEmployeesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CountEmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private CountEmployeesService countEmployeesService;

  @Test
  void givenEmployeesExist_whenCountEmployee_returnEmployeeCount() {
    // given
    final Long employeeCount = 3L;

    // when
    when(employeeRepository.count()).thenReturn(employeeCount);
    final Long response = countEmployeesService.execute(null);

    // then
    assertEquals(employeeCount, response);
    verify(employeeRepository, times(1)).count();
  }

  @Test
  void givenEmployeesDoesNotExist_whenCountEmployee_returnZero() {

    // given & when
    when(employeeRepository.count()).thenReturn(0L);
    final Long response = countEmployeesService.execute(null);

    // then
    assertEquals(0L, response);
    verify(employeeRepository, times(1)).count();
  }
}