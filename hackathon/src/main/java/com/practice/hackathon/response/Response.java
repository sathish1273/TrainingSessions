package com.practice.hackathon.response;

import java.util.List;

import com.practice.hackathon.dto.BusinessMessage;
import com.practice.hackathon.dto.StatusEnum;

public class Response {
	
	private String status;
	private List<BusinessMessage> businessMessage;	
	private Object responseData;
	public List<BusinessMessage> getBusinessMessage() {
		return businessMessage;
	}
	public void setBusinessMessage(List<BusinessMessage> businessMessage) {
		this.businessMessage = businessMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getResponse() {
		return responseData;
	}
	public void setResponse(Object response) {
		this.responseData = response;
	}
	
	

}
