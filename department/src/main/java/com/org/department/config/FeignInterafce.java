package com.org.department.config;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.department.dto.EmployeeDto;

//@FeignClient(name = "Employee-Client", url = "http://localhost:8082/maintanance")
@FeignClient(name="http://maintanance-service/maintanance")
public interface FeignInterafce {
	
    @GetMapping("/port")
	public String getPortNo();
	
	@PostMapping("/addEmployee")
	public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeRequest);
	
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDto>> getEmployees();
	
	@GetMapping("/employeeList")
	public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(@Valid @RequestParam("departmentId") long departmentId);

	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<Boolean> deleteEmployee(@Valid @RequestParam("departmentId") long departmentId, @Valid @RequestParam("employeeId") long employeeId);
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestParam long departmentId, @RequestBody EmployeeDto employeeRequest);
	
	@DeleteMapping("/deleteAllEmployees")
	public ResponseEntity<Boolean> deleteAllEmployees(@Valid @RequestParam("departmentId") long departmentId);
}
