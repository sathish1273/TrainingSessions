package com.org.department.service;

import com.org.department.request.DepartmentRequest;
import com.org.department.response.DepartmentOrdersResponse;

public interface DepartmentService {
	
   public DepartmentOrdersResponse getDepartmentAndEmployees(long departmentId);
   public DepartmentOrdersResponse deleteDepartmetAndEmployees(long departmentId);
   public DepartmentOrdersResponse deleteDepartmetAndEmployee(long departmentId,long employeeId);
   public DepartmentOrdersResponse getAllDepartmentAndEmployees();
   public DepartmentOrdersResponse addDepartment(DepartmentRequest departmentRequest);
   public String getPortNo();
   
}
