package com.org.department.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.org.department.dto.EmployeeDto;

public class DepartmentOrdersResponse {
	
	private String message;
	private Map<String,List<EmployeeDto>> response=new HashMap<String, List<EmployeeDto>>();

	public Map<String, List<EmployeeDto>> getResponse() {
		return response;
	}

	public void setResponse(Map<String, List<EmployeeDto>> response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
