package com.operations.bank.requestValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
			list.add(new BusinessMessage("Request should not be null"));
		else if(Objects.isNull(request.getFromAccount()))
			list.add(new BusinessMessage("Invalid from Account Number"));
		else if(Objects.isNull(request.getToAccount()))
			list.add(new BusinessMessage("Invalid To Account Number"));
		else if(Objects.isNull(request.getAmount()) || request.getAmount() < amount )
			list.add(new BusinessMessage("Amount must be greater than 0"));
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
	
	
	
	public static LocalDate getDate(String date,String type)
	{
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
	}
	
	public static Map<String,String> getMonthNo(String monthName)
	{
		Map<String,String> maplist=new TreeMap<String, String>();
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
