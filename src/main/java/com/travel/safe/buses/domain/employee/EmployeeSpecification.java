package com.travel.safe.buses.domain.employee;

import com.travel.safe.buses.domain.employee.model.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

  private EmployeeSpecification() {
  }

  public static Specification<Employee> hasDepartment(Integer departmentId) {
    return (root, query, builder) -> departmentId == null ? builder.conjunction()
        : builder.equal(root.get("department").get("id"), departmentId);
  }

  public static Specification<Employee> hasSupervisor(Long supervisorId) {
    return (root, query, builder) -> supervisorId == null ? builder.conjunction()
        : builder.equal(root.get("supervisor").get("ssn"), supervisorId);
  }
}