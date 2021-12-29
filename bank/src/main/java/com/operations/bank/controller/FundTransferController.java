package com.operations.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.request.FundtransferRquest;
import com.operations.bank.requestValidator.RequestValidator;
import com.operations.bank.response.Response;
import com.operations.bank.service.FundTransferService;

@RestController
public class FundTransferController {

	@Autowired
	FundTransferService fundTransferService;
	
	@PostMapping("/fundTransfer")
	public ResponseEntity<Response> fundTransfer(@RequestBody FundtransferRquest fundtransferRequest)
	{
		Response response=new Response();
		HttpStatus httpstatus=HttpStatus.OK;
		List<BusinessMessage> list= RequestValidator.validateFundTransferRequest(fundtransferRequest);
		if(!list.isEmpty()) {
			response.setStatus(StatusEnum.FAIL);
			response.setBusinessMessage(list);
			httpstatus=HttpStatus.NOT_FOUND;
		}
		else {
			response=fundTransferService.fundtransfer(fundtransferRequest);
			if(response.getStatus().equals(StatusEnum.FAIL))
			 httpstatus=HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(response,httpstatus);
	}

}
