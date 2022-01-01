package com.org.department.service;

import com.org.department.request.DepartmentRequest;
import com.org.department.response.DepartmentOrdersResponse;

public interface DepartmentService {
	
   public DepartmentOrdersResponse getDepartmentAndEmployees(long departmentId);
   public DepartmentOrdersResponse getAllDepartmentAndEmployees();
   public DepartmentOrdersResponse addDepartment(DepartmentRequest departmentRequest);
   
}
