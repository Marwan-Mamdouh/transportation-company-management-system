package com.travelsave.buses.employee;

import com.travelsave.buses.employee.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

  @Query("select (count(e) > 0) from Employee e where e.employeeId = :employeeId")
  boolean existsByEmployeeId(@Param("employeeId") @NonNull String employeeId);

  @Query("select (count(e) > 0) from Employee e where upper(e.email) = upper(:email)")
  boolean existsByEmailIgnoreCase(@Param("email") @NonNull String email);

  @Query("select (count(e) > 0) from Employee e where e.phoneNumber = :phoneNumber")
  boolean existsByPhoneNumber(@Param("phoneNumber") @NonNull String phoneNumber);

  @Query("select (count(e) > 0) from Employee e where e.department.id = :id")
  boolean existsByDepartment_Id(@Param("id") @NonNull Integer id);

  @Query("select (count(e) > 0) from Employee e where e.supervisor.employeeId = :employeeId")
  boolean existsBySupervisor_EmployeeId(@Param("employeeId") @NonNull String employeeId);

  @Query("select e from Employee e where e.supervisor.employeeId = :employeeId order by e.employeeId")
  List<Employee> findBySupervisor_EmployeeIdOrderByEmployeeIdAsc(
      @Param("employeeId") @NonNull String employeeId);

  @Query("select e from Employee e where e.department.id = :id order by e.employeeId")
  List<Employee> findByDepartment_IdOrderByEmployeeIdAsc(@Param("id") @NonNull Integer id);

}