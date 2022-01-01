package com.employee.maintanance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.maintanance.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
 
	public List<Employee> findByDepartmentId(long id);
}
