package com.practice.hackathon.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestData {
	@NotNull(message = "PlanId should be not be empty")
	private long planId;
	@NotNull(message = "UserIdentificationId should be not be empty")
	private long userIdentificationId;
	@NotNull(message = "MobileNumber should be not be empty")
	private long mobileNumber;
	@NotEmpty(message = "RequestStatus should be not be empty")
	private String requestStatus;
	private String comments;
	public long getPlanId() {
		return planId;
	}
	public void setPlanId(long planId) {
		this.planId = planId;
	}
	public long getUserIdentificationId() {
		return userIdentificationId;
	}
	public void setUserIdentificationId(long userIdentificationId) {
		this.userIdentificationId = userIdentificationId;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
