package com.travelSafe.buses.domain.employee.services;

import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.exceptions.employee.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailService implements UserDetailsService {

  private final EmployeeRepository employeeRepository;
  private static final Logger logger = LoggerFactory.getLogger(EmployeeDetailService.class);

  public EmployeeDetailService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    logger.info("Executing: {} with input: {}", getClass(), email);
    final var user = employeeRepository.findByEmail(email)
        .orElseThrow(EmployeeNotFoundException::new);
    return User.withUsername(user.getEmail()).password(user.getPassword())
        //.authorities(user.getAuthorities())
        .build();
  }
}