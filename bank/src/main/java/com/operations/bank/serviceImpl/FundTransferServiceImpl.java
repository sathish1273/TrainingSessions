package com.operations.bank.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.enity.Account;
import com.operations.bank.enity.Transactions;
import com.operations.bank.repository.AccountRepository;
import com.operations.bank.repository.TransactionsRepository;
import com.operations.bank.request.FundtransferRquest;
import com.operations.bank.requestValidator.RequestValidator;
import com.operations.bank.response.Response;
import com.operations.bank.service.FundTransferService;

@Service
public class FundTransferServiceImpl implements FundTransferService {
	
	@Autowired
	TransactionsRepository transactionsRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Response fundtransfer(FundtransferRquest request) {
		Response response=new Response();
		List<BusinessMessage> list=new ArrayList<>();
		Account toAccount=RequestValidator.getAccount(request.getToAccount());
		Account fromAccount=RequestValidator.getAccount(request.getToAccount());
		if(toAccount != null && fromAccount != null) {
			fromAccount.setOpeningBal(fromAccount.getOpeningBal()-request.getAmount());
			toAccount.setOpeningBal(fromAccount.getOpeningBal()+request.getAmount());
			accountRepository.save(toAccount);
			accountRepository.save(fromAccount);
			Transactions t=new Transactions(RequestValidator.getAccount(request.getFromAccount()), RequestValidator.getAccount(request.getToAccount()), request.getAmount(), request.getComments());
			Transactions transaction=transactionsRepository.save(t);
			if(transaction != null)
			{
				list.add(new BusinessMessage("Fund Transfer done successfully."));
				response.setStatus(StatusEnum.SUCCESS);
				response.setBusinessMessage(list);
				response.setResponse("Transfered Amount : "+transaction.getAmount());
			}
		}
		return response;
	}
	

}
