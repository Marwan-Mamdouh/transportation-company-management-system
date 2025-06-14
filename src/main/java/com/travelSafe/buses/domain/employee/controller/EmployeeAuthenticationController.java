package com.travelSafe.buses.domain.employee.controller;

import com.travelSafe.buses.domain.employee.model.dto.EmployeeLoginDTO;
import com.travelSafe.buses.domain.employee.model.dto.EmployeeResponseDTO;
import com.travelSafe.buses.domain.employee.model.dto.InputEmployeeDTO;
import com.travelSafe.buses.domain.employee.services.CreateEmployeeService;
import com.travelSafe.buses.domain.employee.services.EmployeeLoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class EmployeeAuthenticationController {

  private final EmployeeLoginService employeeLoginService;
  private final CreateEmployeeService createEmployeeService;

  public EmployeeAuthenticationController(EmployeeLoginService employeeLoginService,
      CreateEmployeeService createEmployeeService) {
    this.employeeLoginService = employeeLoginService;
    this.createEmployeeService = createEmployeeService;
  }

  @PostMapping("/register")
  public ResponseEntity<EmployeeResponseDTO> createNewEmployee(
      @Valid @RequestBody InputEmployeeDTO empLoginCre) {
    return ResponseEntity.ok(new EmployeeResponseDTO(createEmployeeService.execute(empLoginCre)));
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody EmployeeLoginDTO empLoginCre) {
    return ResponseEntity.ok(employeeLoginService.execute(empLoginCre));
  }

//  @PostMapping("/refresh")
//  public ResponseEntity<String> refresh(@RequestBody EmployeeLoginDTO empLoginCre) {
//    return null;
//  }
//
//  @PostMapping("/logout")
//  public ResponseEntity<String> logout(@RequestBody EmployeeLoginDTO empLoginCre) {
//    return null;
//  }
}