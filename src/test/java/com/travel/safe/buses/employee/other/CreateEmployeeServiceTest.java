package com.travel.safe.buses.employee.other;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.employee.EmployeeMapper;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.InputEmployeeDTO;
import com.travel.safe.buses.domain.employee.exceptions.DuplicateEmployeeEmailException;
import com.travel.safe.buses.domain.employee.exceptions.DuplicateEmployeeIdException;
import com.travel.safe.buses.domain.employee.exceptions.DuplicateEmployeePhoneNumberException;
import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.employee.services.CreateEmployeeService;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class CreateEmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private EmployeeMapper employeeMapper;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private CreateEmployeeService createEmployeeService;

  @Test
  void givenValidDataForEmployee_whenCreateEmployeeService_return() {
    // given
    InputEmployeeDTO input = new InputEmployeeDTO(21341234569803L, "John", "Doe",
        "john.doe@example.com", "123456789", "01234567890", LocalDate.parse("2002-01-10"));
    Employee employee = new Employee();
    when(employeeRepository.existsBySsn(input.ssn())).thenReturn(false);
    when(employeeRepository.existsByEmail(input.email())).thenReturn(false);
    when(employeeRepository.existsByPhoneNumber(input.phoneNumber())).thenReturn(false);
    when(employeeMapper.employeeFromInputDTO(input)).thenReturn(employee);
    when(employeeRepository.save(employee)).thenReturn(employee);

    // when
    final Employee result = createEmployeeService.execute(input);

    // then
    assertNotNull(result);
    assertEquals(result, employee);
    verify(employeeRepository, times(1)).save(employee);
  }

  @Test
  void givenNotValidEmailForEmployee_whenCreateEmployeeService_return() {
    // given
    InputEmployeeDTO input = new InputEmployeeDTO(21341234569803L, "John", "Doe",
        "john.doe@example.com", "123456789", "1234567890", LocalDate.parse("2002-01-10"));
    when(employeeRepository.existsByEmail(input.email())).thenReturn(true);

    // when & then
    assertThrows(DuplicateEmployeeEmailException.class, () -> createEmployeeService.execute(input));
  }

  @Test
  void givenNotValidSSNForEmployee_whenCreateEmployeeService_return() {
    // given
    InputEmployeeDTO input = new InputEmployeeDTO(21341234569803L, "John", "Doe",
        "john.doe@example.com", "123456789", "1234567890", LocalDate.parse("2002-01-10"));
    when(employeeRepository.existsBySsn(input.ssn())).thenReturn(true);

    // when & then
    assertThrows(DuplicateEmployeeIdException.class, () -> createEmployeeService.execute(input));
  }

  @Test
  void givenNotValidPhoneNumberForEmployee_whenCreateEmployeeService_return() {
    // given
    InputEmployeeDTO input = new InputEmployeeDTO(21341234569803L, "John", "Doe",
        "john.doe@example.com", "123456789", "1234567890", LocalDate.parse("2002-01-10"));
    when(employeeRepository.existsByPhoneNumber(input.phoneNumber())).thenReturn(true);

    // when & then
    assertThrows(DuplicateEmployeePhoneNumberException.class,
        () -> createEmployeeService.execute(input));
  }
}