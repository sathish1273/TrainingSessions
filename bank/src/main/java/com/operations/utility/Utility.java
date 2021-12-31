package com.operations.utility;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.operations.bank.enity.Account;
import com.operations.bank.repository.AccountRepository;

public class Utility {
	
	@Autowired
    AccountRepository accountRepository;
	
	public Account getAccount(long accountNo)
	{
		Optional<Account> account=accountRepository.findByAccountNo(accountNo);
		if(account.isPresent())
		   return account.get();
		else
		   return null;
	}

}
