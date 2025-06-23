package com.travel.safe.buses.domain.employee;

import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.employee.model.EmployeeAuth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>,
    JpaSpecificationExecutor<Employee> {

  @Query("""
      select (count(e) > 0) from Employee e
      where e.ssn = :ssn or e.phoneNumber = :phoneNumber or upper(e.email) = upper(:email)""")
  boolean existsBySsnOrPhoneNumberOrEmailIgnoreCase(@Param("ssn") @Nullable Long ssn,
      @Param("phoneNumber") @NonNull String phoneNumber, @Param("email") @Nullable String email);

  @Query("SELECT e.email AS email, e.password AS password, e.role AS role FROM Employee e WHERE e.email = :email")
  Optional<EmployeeAuth> findByEmail(@Param("email") @NonNull String email);
}