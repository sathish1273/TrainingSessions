package com.operations.bank.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.operations.bank.constants.BusinessValidationMessageConstants;
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
import com.operations.utility.Utility;

@Service
@Transactional
public class FundTransferServiceImpl implements FundTransferService {
	
	@Autowired
	TransactionsRepository transactionsRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	RequestValidator requestValidator;
	
	@Autowired
	Utility utility;
	
	@Override
	public Response fundtransfer(FundtransferRquest request) {
		Response response=new Response();
		List<BusinessMessage> list=new ArrayList<>();
		Account toAccount=utility.getAccount(request.getToAccount());
		if(Objects.isNull(toAccount)) {
			list.add(new BusinessMessage(BusinessValidationMessageConstants.INVALID_TO_ACCOUNT));
			response.setStatus(StatusEnum.FAIL);
			response.setBusinessMessage(list);
			return response;
		}
		Account fromAccount=utility.getAccount(request.getFromAccount());
		if(!Objects.isNull(toAccount) && !Objects.isNull(fromAccount)) {		
			if(fromAccount.getAvailableBal() <= 0 || fromAccount.getAvailableBal() < request.getAmount()) {
				list.add(new BusinessMessage(BusinessValidationMessageConstants.INSUFFIECIENT_FUNDS));
				response.setStatus(StatusEnum.FAIL);
				response.setBusinessMessage(list);
				return response;
			}
			fromAccount.setAvailableBal(fromAccount.getAvailableBal()-request.getAmount());
			toAccount.setAvailableBal(toAccount.getAvailableBal()+request.getAmount());
			accountRepository.save(toAccount);
			accountRepository.save(fromAccount);
			Transactions t=new Transactions(request.getFromAccount(), request.getToAccount(), request.getAmount(), request.getComments(), LocalDateTime.now());
			Transactions transaction=transactionsRepository.save(t);
			if(!Objects.isNull(transaction))
			{
				list.add(new BusinessMessage(BusinessValidationMessageConstants.FUNDS_TRANSFER_SUCCESS));
				response.setStatus(StatusEnum.SUCCESS);
				response.setBusinessMessage(list);
				response.setResponse(BusinessValidationMessageConstants.TRANSFER_AMOUNT + transaction.getAmount()+ BusinessValidationMessageConstants.TO_ACCOUNT +toAccount.getAccountNo());
			}
		}
		else
		{
			list.add(new BusinessMessage(BusinessValidationMessageConstants.INVALIDFROM_ACCOUNT));
			response.setStatus(StatusEnum.FAIL);
			response.setBusinessMessage(list);
		}
		return response;
	}
	public boolean validateAccountNumber(long accountNo)
	{
		Optional<Account> account=accountRepository.findByAccountNo(accountNo);
		if(account.isPresent())
		   return true;
		else
		   return false;
	}
}
