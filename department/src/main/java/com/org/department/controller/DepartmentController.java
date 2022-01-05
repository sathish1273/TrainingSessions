package com.org.department.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.department.request.DepartmentRequest;
import com.org.department.response.DepartmentOrdersResponse;
import com.org.department.service.DepartmentService;

@RestController
@Validated
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/portNumber")
	public String getPortNo()
	{
		return departmentService.getPortNo();
	}
	
	@GetMapping("/departments")
	public ResponseEntity<DepartmentOrdersResponse> getDepartmentEmployees()
	{
		DepartmentOrdersResponse response=departmentService.getAllDepartmentAndEmployees();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<DepartmentOrdersResponse> getDepartmentEmployee(@Valid @PathVariable("departmentId") long departmentId)
	{
		DepartmentOrdersResponse response=departmentService.getDepartmentAndEmployees(departmentId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	

//	@GetMapping("/department")
//	public ResponseEntity<DepartmentOrdersResponse> getDepartmentEmployeeList(@Valid @RequestParam("departmentId") long departmentId)
//	{
//		DepartmentOrdersResponse response=departmentService.getDepartmentAndEmployees(departmentId);
//		return new ResponseEntity<>(response,HttpStatus.OK);
//	}
	
	@PostMapping("/addDepartmentEmployees")
	public ResponseEntity<DepartmentOrdersResponse> addDepartment(@Valid @RequestBody DepartmentRequest departmentRequest)
	{
		DepartmentOrdersResponse response=departmentService.addDepartment(departmentRequest);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/updateEmployee")
	public ResponseEntity<DepartmentOrdersResponse> updateEmployee(@Valid @RequestBody DepartmentRequest departmentRequest)
	{
		DepartmentOrdersResponse response=departmentService.addDepartment(departmentRequest);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/deleteAllEmployees")
	public ResponseEntity<DepartmentOrdersResponse> deleteAllEmployeesWithDepartment(@Valid @RequestParam("departmentId") long departmentId)
	{
		DepartmentOrdersResponse response=departmentService.deleteDepartmetAndEmployees(departmentId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/deleteEmployee")
	public ResponseEntity<DepartmentOrdersResponse> deleteOneEmployee(@Valid @RequestParam("departmentId") long departmentId,@Valid @RequestParam("employeeId") long employeeId)
	{
		DepartmentOrdersResponse response=departmentService.deleteDepartmetAndEmployee(departmentId,employeeId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
