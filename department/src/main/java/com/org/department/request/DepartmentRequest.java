package com.org.department.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.org.department.dto.EmployeeDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentRequest {
	
	@NotNull(message = "Department Name Should not be null.")
	private String departmentName;
	private List<EmployeeDto> employeeDto=new ArrayList<>();

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<EmployeeDto> getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(List<EmployeeDto> employeeDto) {
		this.employeeDto = employeeDto;
	}

}
