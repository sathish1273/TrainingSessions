package com.employee.maintanance.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.maintanance.entity.Employee;
import com.employee.maintanance.repository.EmployeeRepository;
import com.employee.maintanance.request.EmployeeRequest;
import com.employee.maintanance.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee getEmployee(long id) {
		Optional<Employee> employee=employeeRepository.findById(id);
		if(employee.isPresent())
			return employee.get();
		else
		    return null;
	}

	@Override
	public Employee addEmployee(EmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.save(new Employee(employeeRequest.getName(), employeeRequest.getAge(), employeeRequest.getDepartmentId()));
		return employee;
	}

	@Override
	public List<Employee> getEmployeesByDepartment(long departmentId) {
		List<Employee> employees=employeeRepository.findByDepartmentId(departmentId);
		    return employees;
	}

	@Override
	public List<Employee> getEmployee() {

		List<Employee> employee=employeeRepository.findAll();
		    return employee;
	
	}

	@Override
	public Employee updateEmployee(long departmentId, EmployeeRequest employeeRequest) {
		Employee employee=employeeRepository.findByIdAndDepartmentId(departmentId, employeeRequest.getId());
		if(!Objects.isNull(employee)) {
			employee.setAge(employeeRequest.getAge());
			employee.setDepartmentId(employeeRequest.getDepartmentId());
			employee.setName(employeeRequest.getName());
			employee=employeeRepository.save(employee);
			return employee;
		}
		else {
		return null;
		}
	}

	@Override
	public boolean deleteEmployee(long departmentId,long employeeId) {
		Employee employee=employeeRepository.findByIdAndDepartmentId(departmentId, employeeId);
		employeeRepository.delete(employee);	
		if(!Objects.isNull(employee) && Objects.isNull(getEmployee(employee.getId())))
		   return true;
		else
			return false;
	}

	@Override
	public boolean deleteAllEmployees(long departmentId) {
		List<Employee> employees=getEmployeesByDepartment(departmentId);
		if(employees.size()>0) {
		employees.stream().forEach(employee->{
			deleteEmployee(employee.getDepartmentId(), employee.getId());
		});
		return true;
		}
		else {
			return false;
		}
		
	}

}
