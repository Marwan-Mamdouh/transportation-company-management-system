package com.travelSafe.buses.employee.controller;

import com.travelSafe.buses.employee.model.DTO.EmployeeLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class EmployeeLoginController {

  @PostMapping("/register")
  public ResponseEntity<String> createNewEmployee(
      @RequestBody EmployeeLoginDTO empLoginCre) {
    return null;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody EmployeeLoginDTO empLoginCre) {
    return null;
  }

  @PostMapping("/refresh")
  public ResponseEntity<String> refresh(@RequestBody EmployeeLoginDTO empLoginCre) {
    return null;
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(@RequestBody EmployeeLoginDTO empLoginCre) {
    return null;
  }
}