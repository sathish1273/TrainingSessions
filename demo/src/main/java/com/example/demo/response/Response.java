package com.example.demo.response;

import java.util.List;

public class Response {
	
	private List<BusinessMessage> businessMessage;
	private StatusEnum status;
	private Object resObj;
	public List<BusinessMessage> getBusinessMessage() {
		return businessMessage;
	}
	public void setBusinessMessage(List<BusinessMessage> businessMessage) {
		this.businessMessage = businessMessage;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public Object getResObj() {
		return resObj;
	}
	public void setResObj(Object resObj) {
		this.resObj = resObj;
	}
	

}
