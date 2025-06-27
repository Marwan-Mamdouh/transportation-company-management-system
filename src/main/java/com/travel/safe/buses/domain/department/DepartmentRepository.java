package com.travel.safe.buses.domain.department;

import com.travel.safe.buses.domain.department.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>,
    JpaSpecificationExecutor<Department> {

  Page<Department> findByName(String name, Pageable pageable);
}