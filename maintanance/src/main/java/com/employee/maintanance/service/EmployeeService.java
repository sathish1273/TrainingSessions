package com.employee.maintanance.service;

import java.util.List;

import com.employee.maintanance.entity.Employee;
import com.employee.maintanance.request.EmployeeRequest;

public interface EmployeeService {
	
	public Employee getEmployee(long id);
	public List<Employee> getEmployee();
	public List<Employee> getEmployeesByDepartment(long id);
	public Employee addEmployee(EmployeeRequest employeeRequest);
	public Employee updateEmployee(long id, EmployeeRequest employeeRequest);
	public boolean deleteEmployee(long departmentId,long employeeId);
	public boolean deleteAllEmployees(long departmentId);

}
