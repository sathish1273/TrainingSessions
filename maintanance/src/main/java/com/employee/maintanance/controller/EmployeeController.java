package com.employee.maintanance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.maintanance.entity.Employee;
import com.employee.maintanance.request.EmployeeRequest;
import com.employee.maintanance.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	Environment environment;
	
    @GetMapping("/port")
	public String getPortNo()
	{
		return "Maintanance service port number:  "+environment.getProperty("local.server.port");
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getEmployees()
	{
		List<Employee> response=employeeService.getEmployee();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployees(@Valid @PathVariable("id") long id)
	{
		Employee response=employeeService.getEmployee(id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/employeeList")
	public ResponseEntity<List<Employee>> getEmployeesByDepartment(@Valid @RequestParam("departmentId") long departmentId)
	{
		List<Employee> response=employeeService.getEmployeesByDepartment(departmentId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addDepartment(@Valid @RequestBody EmployeeRequest employeeRequest)
	{
		Employee response=employeeService.addEmployee(employeeRequest);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<Boolean> deleteEmployee(@Valid @RequestParam("departmentId") long departmentId, @Valid @RequestParam("employeeId") long employeeId)
	{
		boolean response=employeeService.deleteEmployee(departmentId,employeeId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAllEmployees")
	public ResponseEntity<Boolean> deleteEmployee(@Valid @RequestParam("departmentId") long departmentId)
	{
		boolean response=employeeService.deleteAllEmployees(departmentId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestParam long departmentId, @RequestBody EmployeeRequest employeeRequest)
	{
		Employee response=employeeService.updateEmployee(departmentId, employeeRequest);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
