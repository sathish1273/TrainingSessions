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
import org.springframework.web.bind.annotation.RestController;

import com.org.department.request.DepartmentRequest;
import com.org.department.response.DepartmentOrdersResponse;
import com.org.department.service.DepartmentService;

@RestController
@Validated
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/department")
	public ResponseEntity<DepartmentOrdersResponse> getDepartmentEmployees()
	{
		DepartmentOrdersResponse response=departmentService.getAllDepartmentAndEmployees();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/department/{id}")
	public ResponseEntity<DepartmentOrdersResponse> getDepartmentEmployees(@Valid @PathVariable("id") long id)
	{
		DepartmentOrdersResponse response=departmentService.getDepartmentAndEmployees(id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/addDepartmentEmployees")
	public ResponseEntity<DepartmentOrdersResponse> addDepartment(@Valid @RequestBody DepartmentRequest departmentRequest)
	{
		DepartmentOrdersResponse response=departmentService.addDepartment(departmentRequest);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
