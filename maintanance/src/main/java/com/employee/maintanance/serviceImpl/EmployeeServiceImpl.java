package com.employee.maintanance.serviceImpl;

import java.util.List;
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
	public List<Employee> getEmployeesByDepartment(long id) {
		List<Employee> employees=employeeRepository.findByDepartmentId(id);
		    return employees;
	}

	@Override
	public List<Employee> getEmployee() {

		List<Employee> employee=employeeRepository.findAll();
		    return employee;
	
	}

}
