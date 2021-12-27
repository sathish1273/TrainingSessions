package com.operations.bank.requestValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.enity.Account;
import com.operations.bank.repository.AccountRepository;
import com.operations.bank.request.FundtransferRquest;
import com.operations.bank.request.UserRequest;

public class RequestValidator {
	
	@Autowired
	static AccountRepository accountRepository;
	
	public static final int age=18;
	public static final int amount=0;
	
	public static List<BusinessMessage> validateFundTransferRequest(FundtransferRquest request)
	{
		
		List<BusinessMessage> list=new ArrayList<>();
		if(Objects.isNull(request))
			list.add(new BusinessMessage("Request should not be null"));
		else if(Objects.isNull(request.getFromAccount()) || !validateAccountNumber(request.getFromAccount()))
			list.add(new BusinessMessage("Invalid from Account Number"));
		else if(Objects.isNull(request.getToAccount()) || !validateAccountNumber(request.getFromAccount()))
			list.add(new BusinessMessage("Invalid To Account Number"));
		else if(Objects.isNull(request.getAmount()) || request.getAmount() > amount )
			list.add(new BusinessMessage("Amount must be greater than 0"));
		else if(Objects.isNull(request.getFromAccount()) || getAccount(request.getFromAccount()).getOpeningBal() < request.getAmount())
			list.add(new BusinessMessage("Insufficient funds in from Account."));
		
		return list;
	}
	public static List<BusinessMessage> validateRequest(UserRequest request)
	{
		List<BusinessMessage> list=new ArrayList<>();
		if(Objects.isNull(request))
			list.add(new BusinessMessage("Request should not be null"));
		else if(Objects.isNull(request.getFname()) || request.getFname().isEmpty() || request.getFname().isBlank())
			list.add(new BusinessMessage("FirstName should not be null or blank"));
		else if(Objects.isNull(request.getLname()) || request.getLname().isEmpty() || request.getLname().isBlank())
			list.add(new BusinessMessage("LastName should not be null or blank"));
		else if(Objects.isNull(request.getAge()) || request.getAge() <= age )
			list.add(new BusinessMessage("Age must be greater than or equal to 18"));
		else if(Objects.isNull(request.getPhoneNo()) || !isValidMobileNo(String.valueOf(request.getPhoneNo())))
			list.add(new BusinessMessage("Enetered Mobile number is invalid"));
		else if(Objects.isNull(request.getEmail()) || !isValidEmail(request.getEmail()))
			list.add(new BusinessMessage("Enetered Email number is invalid"));
		
		return list;
	}
	
	public static boolean isValidMobileNo(String str)  
	{  
	Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
	Matcher match = ptrn.matcher(str);
	return (match.find() && match.group().equals(str));  
	} 
	
	public static boolean isValidEmail(String str)  
	{
		 String regex = "^(.+)@(.+)$"; 
	     Pattern pattern = Pattern.compile(regex); 
	     Matcher matcher = pattern.matcher(str);  
	     return matcher.matches();
	}
	
	public static boolean validateAccountNumber(int accountNo)
	{
		Optional<Account> account=accountRepository.findByAccountNo(accountNo);
		if(account.isPresent())
		   return true;
		else
		   return false;
	}
	
	public static Account getAccount(int accountNo)
	{
		Optional<Account> account=accountRepository.findByAccountNo(accountNo);
		if(account.isPresent())
		   return account.get();
		else
		   return null;
	}

}
