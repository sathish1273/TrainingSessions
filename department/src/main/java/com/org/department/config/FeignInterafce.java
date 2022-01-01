package com.org.department.config;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.department.dto.EmployeeDto;

@FeignClient(name = "Employee-Client", url = "http://localhost:8082")
public interface FeignInterafce {
	
	@PostMapping("/addEmployee")
	public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeRequest);
	
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDto>> getEmployees();
	
	@GetMapping("/employeeList/{departmentId}")
	public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(@Valid @PathVariable("departmentId") long departmentId);

}
