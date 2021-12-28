package com.operations.bank.requestValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.operations.bank.constants.BusinessValidationMessageConstants;
import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.repository.AccountRepository;
import com.operations.bank.request.FundtransferRquest;
import com.operations.bank.request.UserRequest;

@Component
public class RequestValidator {
	
	@Autowired
	static AccountRepository accountRepository;
	
	public static final int age=18;
	public static final int amount=0;
	
	public static List<BusinessMessage> validateFundTransferRequest(FundtransferRquest request)
	{
		
		List<BusinessMessage> list=new ArrayList<>();
		if(Objects.isNull(request))
			list.add(new BusinessMessage(BusinessValidationMessageConstants.REQUEST_NOTNULL));
		else if(Objects.isNull(request.getFromAccount()))
			list.add(new BusinessMessage(BusinessValidationMessageConstants.INVALID_ACCOUNTNO));
		else if(Objects.isNull(request.getToAccount()))
			list.add(new BusinessMessage(BusinessValidationMessageConstants.INVALID_TO_ACCOUNTNO));
		else if(Objects.isNull(request.getAmount()) || request.getAmount() <= amount )
			list.add(new BusinessMessage(BusinessValidationMessageConstants.AMOUNT_GREATERTHAN_ZERO));
		return list;
	}
	public static List<BusinessMessage> validateRequest(UserRequest request)
	{
		List<BusinessMessage> list=new ArrayList<>();
		if(Objects.isNull(request))
			list.add(new BusinessMessage(BusinessValidationMessageConstants.REQUEST_NOTNULL));
		else if(Objects.isNull(request.getFname()) || request.getFname().isEmpty() || request.getFname().isBlank())
			list.add(new BusinessMessage(BusinessValidationMessageConstants.FIRST_NOTNULL));
		else if(Objects.isNull(request.getLname()) || request.getLname().isEmpty() || request.getLname().isBlank())
			list.add(new BusinessMessage(BusinessValidationMessageConstants.LAST_NOTNULL));
		else if(Objects.isNull(request.getAge()) || request.getAge() < age )
			list.add(new BusinessMessage(BusinessValidationMessageConstants.AGE_GREATERTHAN_EIGHTEEN));
		else if(Objects.isNull(request.getPhoneNo()) || !isValidMobileNo(String.valueOf(request.getPhoneNo())))
			list.add(new BusinessMessage(BusinessValidationMessageConstants.MOBILENO_INVALID));
		else if(Objects.isNull(request.getEmail()) || !isValidEmail(request.getEmail()))
			list.add(new BusinessMessage(BusinessValidationMessageConstants.EMAIL_INVALID));
		
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
	
	
	
	public static LocalDate getDate(String date,String type)
	{
		try {
			date=date.trim();
			String month=null;
			LocalDate today = null;
			if(date != null && date.length()>7 && date.contains(" ")) {
			Map<String,String> maplist= getMonthNo(date);
			for(Map.Entry<String,String> entry : maplist.entrySet())
			{
				if(entry.getValue().contains(date.substring(0, date.length()-5)))
				{
				month=entry.getKey();
				break;
				}
			}
			String dates="01";
			if(type.equals("endDate"))
				dates=month.substring(2, 4);
			today = LocalDate.parse(date.substring(date.length()-4,date.length())+"-"+month.substring(0, 2)+"-"+dates);	
			}
			return today;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public static Map<String,String> getMonthNo(String monthName)
	{
		Map<String,String> maplist=new TreeMap<>();
		maplist.put("0131", "January");
		maplist.put("0228", "Febrauary");
		maplist.put("0331", "March");
		maplist.put("0430", "April");
		maplist.put("0531", "May");
		maplist.put("0630", "June");
		maplist.put("0731", "July");
		maplist.put("0831", "August");
		maplist.put("0930", "September");
		maplist.put("1031", "October");
		maplist.put("1130", "November");
		maplist.put("1231", "December");
		return maplist;
	}

}
