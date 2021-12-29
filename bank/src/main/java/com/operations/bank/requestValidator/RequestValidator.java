package com.operations.bank.requestValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
		if(request.getAge() < age )
			list.add(new BusinessMessage(BusinessValidationMessageConstants.AGE_GREATERTHAN_EIGHTEEN));
		if(!isValidMobileNo(String.valueOf(request.getPhoneNo())))
			list.add(new BusinessMessage(BusinessValidationMessageConstants.MOBILENO_INVALID));
		
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
	
	
	
	public static LocalDate getDate(String monthh,String year,String type)
	{
		try {
			if(Objects.isNull(monthh) || Objects.isNull(year))
				return null;
			monthh=monthh.trim();
			year=year.trim();
			String month=null;
			LocalDate today = null;
			if(!Objects.isNull(monthh) && monthh.length()>2) {
			Map<String,String> maplist= getMonthNo();
			for(Map.Entry<String,String> entry : maplist.entrySet())
			{
				if(entry.getValue().contains(monthh))
				{
				month=entry.getKey();
				break;
				}
			}
			String dates="01";
			if(type.equals("endDate"))
				dates=month.substring(2, 4);
			today = LocalDate.parse(year+"-"+month.substring(0, 2)+"-"+dates);	
			}
			return today;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public static Map<String,String> getMonthNo()
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
