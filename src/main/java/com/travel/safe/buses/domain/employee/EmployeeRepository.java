package com.travel.safe.buses.domain.employee;

import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.employee.model.EmployeeAuth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>,
    JpaSpecificationExecutor<Employee> {

  boolean existsBySsnOrPhoneNumberOrEmailIgnoreCase(@Nullable Long ssn, @NonNull String phoneNumber,
      @Nullable String email);

  Optional<EmployeeAuth> findByEmail(@NonNull String email);
}