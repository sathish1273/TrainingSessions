package com.operations.bank.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operations.bank.constants.BusinessValidationMessageConstants;
import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.enity.Account;
import com.operations.bank.enity.User;
import com.operations.bank.repository.AccountRepository;
import com.operations.bank.repository.UserRepository;
import com.operations.bank.request.UserRequest;
import com.operations.bank.response.Response;
import com.operations.bank.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public static final double initialOpeningBalance=5000;
	public static final int accountNumberLength=10;

	@Override
	public Response addUser(UserRequest request) {
		Response response=new Response();
		List<BusinessMessage> list= new ArrayList<>();
		List<User> uList=userRepository.findByFnameAndLnameAndPhoneNo(request.getFname(), request.getLname(), request.getPhoneNo());
		if(!uList.isEmpty())
		{
			list.add(new BusinessMessage(BusinessValidationMessageConstants.DUPLICATE_USER));
			response.setStatus(StatusEnum.FAIL);
			response.setBusinessMessage(list);
			return response;
		}
			User u = new User(request.getFname(), request.getLname(), request.getAge(), request.getuID(), request.getPhoneNo(), request.getEmail());
			User user=userRepository.save(u);
			if(!Objects.isNull(user))
			{
				Optional<User> uu=userRepository.findById(user.getUserId());
				if(uu.isPresent()) {
					Account account=new Account(generateAccountNo(accountNumberLength), LocalDate.now(), LocalTime.now(), initialOpeningBalance, user,request.getAccountType(),initialOpeningBalance);
					Account savedAccount=accountRepository.save(account);
					if(savedAccount != null)
					{
						list.add(new BusinessMessage(BusinessValidationMessageConstants.USER_SAVED));
						response.setStatus(StatusEnum.SUCCESS);
						response.setBusinessMessage(list);
						response.setResponse(BusinessValidationMessageConstants.ACCOUNT_GENERATED + savedAccount.getAccountNo());
					}
				}
			}
		return response;
	}
	
	public static Long generateAccountNo(int length) {
		String numbers="0123456789";
		Random r=new Random();
		char[] otp=new char[length];
		for(int i=0;i<length;i++)
		{
			otp[i]=numbers.charAt(r.nextInt(numbers.length()));
		}
		return Long.parseLong(String.copyValueOf(otp));
	}
	

}
