package com.operations.bank.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.enity.Transactions;
import com.operations.bank.repository.TransactionsRepository;
import com.operations.bank.requestValidator.RequestValidator;
import com.operations.bank.response.Response;
import com.operations.bank.service.StatementService;

@Service
public class StatementServiceImpl implements StatementService{
	
	@Autowired
	TransactionsRepository transactionsRepository;

	@Override
	public Response getTransactions(LocalDate startDate,LocalDate endDate) {
		
		Response response=new Response();
		List<BusinessMessage> list= new ArrayList<BusinessMessage>();
		List<Transactions> transactionsList=transactionsRepository.findByDateBetween(startDate, endDate);
		if(transactionsList.size() > 0) {
		list.add(new BusinessMessage("Transaction successfully featched."));
		response.setStatus(StatusEnum.SUCCESS);
		response.setBusinessMessage(list);
		response.setResponse(transactionsList);
		}
		else{
			list.add(new BusinessMessage("No records found."));
			response.setStatus(StatusEnum.SUCCESS);
			response.setBusinessMessage(list);
		}
		return response;
	}

}
