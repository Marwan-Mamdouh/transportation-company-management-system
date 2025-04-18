package com.travelsave.buses.employee.services;

import com.travelsave.buses.Command;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.exceptions.employee.DuplicateEmployeeEmailException;
import com.travelsave.buses.exceptions.employee.DuplicateEmployeeIdException;
import com.travelsave.buses.exceptions.employee.DuplicateEmployeePhoneNumber;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeService implements Command<Employee, EmployeeDTO> {

  private final EmployeeRepository employeeRepository;

  public CreateEmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional
  public ResponseEntity<EmployeeDTO> execute(Employee input) {
    if (employeeRepository.existsByEmployeeId(input.getEmployeeId())) {
      throw new DuplicateEmployeeIdException();
    } else if (employeeRepository.existsByEmailIgnoreCase(input.getEmail())) {
      throw new DuplicateEmployeeEmailException();
    } else if (employeeRepository.existsByPhoneNumber(input.getPhoneNumber())) {
      throw new DuplicateEmployeePhoneNumber();
    } else {
      // validate the input before send it to db
      Employee savedEmployee = employeeRepository.save(input);
      return ResponseEntity.status(HttpStatus.CREATED).body(new EmployeeDTO(savedEmployee));
    }
  }
}