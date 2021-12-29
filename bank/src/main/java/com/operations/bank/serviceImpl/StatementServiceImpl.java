package com.operations.bank.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.operations.bank.constants.BusinessValidationMessageConstants;
import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.enity.Transactions;
import com.operations.bank.repository.TransactionsRepository;
import com.operations.bank.response.Response;
import com.operations.bank.service.StatementService;

@Service
@Transactional
public class StatementServiceImpl implements StatementService{
	
	@Autowired
	TransactionsRepository transactionsRepository;

	@Override
	public Response getTransactions(LocalDate startDate,LocalDate endDate,long accountNumber) {
		
		Response response=new Response();
		List<BusinessMessage> list= new ArrayList<>();
		List<Transactions> transactionsList=transactionsRepository.findByFromAccountOrToAccountAndDateBetween(accountNumber,accountNumber,startDate, endDate);
		if(!transactionsList.isEmpty()) {
		list.add(new BusinessMessage(BusinessValidationMessageConstants.TRANSACTIONS_FETCHED));
		response.setStatus(StatusEnum.SUCCESS);
		response.setBusinessMessage(list);
		response.setResponse(transactionsList);
		}
		else{
			list.add(new BusinessMessage(BusinessValidationMessageConstants.NO_RECORDS));
			response.setStatus(StatusEnum.SUCCESS);
			response.setBusinessMessage(list);
		}
		return response;
	}

	
}
