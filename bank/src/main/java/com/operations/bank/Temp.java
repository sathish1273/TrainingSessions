package com.operations.bank;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Temp {
	public static boolean isValidMobileNo(String str)  
	{  
	Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
	Matcher match = ptrn.matcher(str);
	return (match.find() && match.group().equals(str));  
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
	
	public static LocalDate getstartDate(String date,String startDate)
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
		if(startDate.equals("endDate"))
			dates=month.substring(2, 4);
		today = LocalDate.parse(date.substring(date.length()-4,date.length())+"-"+month.substring(0, 2)+"-"+dates);	
		}
		return today;
	}
	public static void main(String[] args) throws ParseException {
		System.out.println(Temp.getstartDate("Dec 2021    ","endDate"));
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
