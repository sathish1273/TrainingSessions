package com.operations.bank.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.operations.bank.constants.BusinessValidationMessageConstants;
import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.request.FundtransferRquest;
import com.operations.bank.request.UserRequest;
import com.operations.bank.requestValidator.RequestValidator;
import com.operations.bank.response.Response;
import com.operations.bank.service.FundTransferService;
import com.operations.bank.service.RegistrationService;
import com.operations.bank.service.StatementService;

@RestController
@RequestMapping("/bank")
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	FundTransferService fundTransferService;
	
	@Autowired
	StatementService statementService;
	
	Response res=null;
	List<BusinessMessage> bm=null;
	HttpStatus httpstatus=null;
	public static final String startDate="startDate";
	public static final String endDate="endDate";
	
	@GetMapping("/")
	public ResponseEntity<String> healthCheck()
	{
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Response> addUser(@RequestBody UserRequest userRequest)
	{
		res=new Response();
		bm=new ArrayList<BusinessMessage>();
		List<BusinessMessage> list= RequestValidator.validateRequest(userRequest);
		if(list.size() > 0) {
			res.setStatus(StatusEnum.FAIL);
			res.setBusinessMessage(list);
			httpstatus=HttpStatus.NOT_FOUND;
		}
		else {
			res=registrationService.addUser(userRequest);
			httpstatus=HttpStatus.OK;
		}
		return new ResponseEntity<>(res,httpstatus);
	}
	
	@PostMapping("/fundTransfer")
	public ResponseEntity<Response> fundTransfer(@RequestBody FundtransferRquest fundtransferRequest)
	{
		res=new Response();
		bm=new ArrayList<>();
		List<BusinessMessage> list= RequestValidator.validateFundTransferRequest(fundtransferRequest);
		if(list.size() > 0) {
			res.setStatus(StatusEnum.FAIL);
			res.setBusinessMessage(list);
			httpstatus=HttpStatus.NOT_FOUND;
		}
		else {
			res=fundTransferService.fundtransfer(fundtransferRequest);
			httpstatus=HttpStatus.OK;
		}
		return new ResponseEntity<>(res,httpstatus);
	}

	@GetMapping("/statement")
	public ResponseEntity<Response> fundTransfer(@RequestParam String date)
	{
		res=new Response();
		bm=new ArrayList<>();
		LocalDate starDate= RequestValidator.getDate(date,startDate);
		LocalDate lastDate= RequestValidator.getDate(date,endDate);
		if(starDate == null || lastDate == null) {
			bm.add(new BusinessMessage(BusinessValidationMessageConstants.INVALID_DATE));
			res.setStatus(StatusEnum.FAIL);
			res.setBusinessMessage(bm);
			httpstatus=HttpStatus.NOT_FOUND;
		}
		else {
			res=statementService.getTransactions(starDate,lastDate);
			httpstatus=HttpStatus.OK;
		}
		return new ResponseEntity<>(res,httpstatus);
	}
}
