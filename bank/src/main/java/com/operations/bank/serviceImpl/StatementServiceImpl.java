package com.operations.bank.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.operations.bank.constants.BusinessValidationMessageConstants;
import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.enity.Account;
import com.operations.bank.enity.Transactions;
import com.operations.bank.repository.TransactionsRepository;
import com.operations.bank.response.Response;
import com.operations.bank.response.TransactionResponse;
import com.operations.bank.service.StatementService;
import com.operations.utility.Utility;

@Service
@Transactional
public class StatementServiceImpl implements StatementService{
	
	@Autowired
	TransactionsRepository transactionsRepository;
	
	@Autowired
	Utility utility;

	@Override
	public Response getTransactions(LocalDateTime startDate,LocalDateTime endDate,long accountNumber) {
		
		Response response=new Response();
		List<BusinessMessage> list= new ArrayList<>();
		Account toAccount=utility.getAccount(accountNumber);
		if(Objects.isNull(toAccount)) {
			list.add(new BusinessMessage(BusinessValidationMessageConstants.INVALID_ACCOUNT));
			response.setStatus(StatusEnum.FAIL);
			response.setBusinessMessage(list);
			return response;
		}
		List<TransactionResponse> transactionResponseList= new ArrayList<>();
		List<Transactions> transactionsList=transactionsRepository.findByFromAccountOrToAccountAndDateBetween(accountNumber,accountNumber,startDate, endDate);
		if(!transactionsList.isEmpty()) {
			transactionsList.stream().forEach(x->{
				String dateTime=x.getDate().toString();
				TransactionResponse transcationResponse=new TransactionResponse(x.getTransactionId(), x.getFromAccount(), x.getToAccount(), x.getAmount(), x.getComments(), dateTime.replace("T", " ").substring(0, 19));
				transactionResponseList.add(transcationResponse);
			});
		list.add(new BusinessMessage(BusinessValidationMessageConstants.TRANSACTIONS_FETCHED));
		response.setStatus(StatusEnum.SUCCESS);
		response.setBusinessMessage(list);
		response.setResponse(transactionResponseList);
		}
		else{
			list.add(new BusinessMessage(BusinessValidationMessageConstants.NO_RECORDS));
			response.setStatus(StatusEnum.SUCCESS);
			response.setBusinessMessage(list);
		}
		return response;
	}

	
}
