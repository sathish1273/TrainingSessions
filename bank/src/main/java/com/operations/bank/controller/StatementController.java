package com.operations.bank.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.operations.bank.constants.BusinessValidationMessageConstants;
import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.requestValidator.RequestValidator;
import com.operations.bank.response.Response;
import com.operations.bank.service.StatementService;

@RestController
@Validated
public class StatementController {

	@Autowired
	StatementService statementService;
	
	public static final String startDate="startDate";
	public static final String endDate="endDate";
	
	@GetMapping("/statement")
	public ResponseEntity<Response> fundTransfer(@RequestParam long accountNumber,@RequestParam String month,@RequestParam String year)
	{
		Response response=new Response();
		HttpStatus httpstatus=null;
		List<BusinessMessage> businessMessage=new ArrayList<>();
		LocalDate starDate= RequestValidator.getDate(month,year,startDate);
		LocalDate lastDate= RequestValidator.getDate(month,year,endDate);
		if(Objects.isNull(starDate) || Objects.isNull(lastDate)) {
			businessMessage.add(new BusinessMessage(BusinessValidationMessageConstants.INVALID_DATE));
			response.setStatus(StatusEnum.FAIL);
			response.setBusinessMessage(businessMessage);
			httpstatus=HttpStatus.NOT_FOUND;
		}
		else {
			response=statementService.getTransactions(starDate.atStartOfDay(),lastDate.atStartOfDay(),accountNumber);
			httpstatus=HttpStatus.OK;
		}
		return new ResponseEntity<>(response,httpstatus);
	}
}
