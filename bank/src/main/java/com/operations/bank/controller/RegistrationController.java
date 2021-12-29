package com.operations.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.request.UserRequest;
import com.operations.bank.requestValidator.RequestValidator;
import com.operations.bank.response.Response;
import com.operations.bank.service.RegistrationService;

@RestController
@Validated
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@GetMapping("/")
	public ResponseEntity<String> healthCheck()
	{
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Response> addUser(@Valid @RequestBody UserRequest userRequest)
	{
		Response response=new Response();
		HttpStatus httpstatus=null;
		List<BusinessMessage> list= RequestValidator.validateRequest(userRequest);
		if(!list.isEmpty()) {
			response.setStatus(StatusEnum.FAIL);
			response.setBusinessMessage(list);
			httpstatus=HttpStatus.NOT_FOUND;
		}
		else {
			response=registrationService.addUser(userRequest);
			httpstatus=HttpStatus.OK;
		}
		return new ResponseEntity<>(response,httpstatus);
	}
}
