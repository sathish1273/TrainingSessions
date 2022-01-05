package com.org.department.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.department.config.FeignInterafce;
import com.org.department.dto.EmployeeDto;
import com.org.department.entity.Department;
import com.org.department.repository.DepartmentRepository;
import com.org.department.request.DepartmentRequest;
import com.org.department.response.DepartmentOrdersResponse;
import com.org.department.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired(required = true)
	FeignInterafce feignInterafce;

	@Override
	public DepartmentOrdersResponse getDepartmentAndEmployees(long departmentId) {
		
		DepartmentOrdersResponse departmentOrdersResponse=new DepartmentOrdersResponse();
		Map<String,List<EmployeeDto>> response=new HashMap<String, List<EmployeeDto>>();
		List<EmployeeDto> employeDtoList=new ArrayList<EmployeeDto>();
		Optional<Department> department=departmentRepository.findById(departmentId);
		if(department.isPresent()) {
			ResponseEntity<List<EmployeeDto>> responseEntity=feignInterafce.getEmployeesByDepartment(departmentId);
			employeDtoList.addAll(responseEntity.getBody());
			response.put(department.get().getDepartmentName(), employeDtoList);
		}
		departmentOrdersResponse.setResponse(response);	
		return departmentOrdersResponse;
	}

	@Override
	public DepartmentOrdersResponse addDepartment(DepartmentRequest departmentRequest) {

		Map<String,List<EmployeeDto>> responseMap=new HashMap<String, List<EmployeeDto>>();
		DepartmentOrdersResponse response=new DepartmentOrdersResponse();
		Department department=departmentRepository.save(new Department(departmentRequest.getDepartmentName()));
		if(!Objects.isNull(department)) {
			String departName=department.getDepartmentName();
			List<EmployeeDto> employeeDtoList=new ArrayList<EmployeeDto>();
			departmentRequest.getEmployeeDto().forEach(e->{
				e.setDepartmentId(department.getId());
				ResponseEntity<EmployeeDto> responseEntity=feignInterafce.addEmployee(e);
				employeeDtoList.add(responseEntity.getBody());
			});
			responseMap.put(departName, employeeDtoList);
		}
		response.setResponse(responseMap);	
		return response;
	}

	@Override
	public DepartmentOrdersResponse getAllDepartmentAndEmployees() {
		
		DepartmentOrdersResponse departmentOrdersResponse=new DepartmentOrdersResponse();
		Map<String,List<EmployeeDto>> response=new HashMap<String, List<EmployeeDto>>();
		List<Department> department=departmentRepository.findAll();
		department.stream().forEach(d->{
			List<EmployeeDto> employeDtoList=new ArrayList<EmployeeDto>();
			ResponseEntity<List<EmployeeDto>> responseEntity=feignInterafce.getEmployeesByDepartment(d.getId());
			employeDtoList.addAll(responseEntity.getBody());
			response.put(d.getDepartmentName(), employeDtoList);
		});
		departmentOrdersResponse.setResponse(response);	
		return departmentOrdersResponse;
	
	}

	@Override
	public DepartmentOrdersResponse deleteDepartmetAndEmployees(long departmentId) {
		
		DepartmentOrdersResponse departmentOrdersResponse=new DepartmentOrdersResponse();
		Map<String,List<EmployeeDto>> response=new HashMap<String, List<EmployeeDto>>();
		List<EmployeeDto> employeDtoList=new ArrayList<EmployeeDto>();
		Optional<Department> department=departmentRepository.findById(departmentId);
		if(department.isPresent()) {
			ResponseEntity<List<EmployeeDto>> responseEntity=feignInterafce.getEmployeesByDepartment(departmentId);
			employeDtoList.addAll(responseEntity.getBody());
			response.put(department.get().getDepartmentName(), employeDtoList);
		}
		departmentOrdersResponse.setResponse(response);	
		return departmentOrdersResponse;
	}

	@Override
	public DepartmentOrdersResponse deleteDepartmetAndEmployee(long departmentId, long employeeId) {
		ResponseEntity<Boolean> response= feignInterafce.deleteEmployee(departmentId, employeeId);
		DepartmentOrdersResponse departmentOrdersResponse=new DepartmentOrdersResponse();
		if(response.getBody().equals(true))
		departmentOrdersResponse.setMessage("Success");
		return departmentOrdersResponse;
	}

	@Override
	public String getPortNo() {
		return feignInterafce.getPortNo();
	}

}
