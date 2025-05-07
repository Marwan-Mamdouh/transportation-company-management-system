package com.travelSafe.buses.employee;

import com.travelSafe.buses.employee.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  @Query("select (count(e) > 0) from Employee e where e.ssn = :ssn")
  boolean existsBySsn(@Param("ssn") @NonNull Long ssn);

  @Query("select (count(e) > 0) from Employee e where e.email = :email")
  boolean existsByEmail(@Param("email") @NonNull String email);

  @Query("select (count(e) > 0) from Employee e where e.phoneNumber = :phoneNumber")
  boolean existsByPhoneNumber(@Param("phoneNumber") @NonNull String phoneNumber);

  @Query("select (count(e) > 0) from Employee e where e.department.id = :id")
  boolean existsByDepartment_Id(@Param("id") @NonNull Integer id);

  @Query("select (count(e) > 0) from Employee e where e.supervisor.ssn = :employeeId")
  boolean existsBySupervisor_EmployeeId(@Param("employeeId") @NonNull Long employeeId);

  @Query("select e from Employee e where e.supervisor.ssn = :employeeId")
  List<Employee> findBySupervisor_EmployeeId(@Param("employeeId") @NonNull Long employeeId);

  @Query("select e from Employee e where e.department.id = :id")
  List<Employee> findByDepartmentId(@Param("id") @NonNull Integer id);

}