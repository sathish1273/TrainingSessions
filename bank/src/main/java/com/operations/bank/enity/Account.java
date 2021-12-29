package com.operations.bank.enity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long accountId;
	
	@Column(nullable=false)
	private long accountNo;
	
	private String accountType;
	private LocalDateTime createdDate;
	private double openingBal;
	private double availableBal;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = true, updatable = true)
	private User user;
	
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public double getAvailableBal() {
		return availableBal;
	}
	public void setAvailableBal(double availableBal) {
		this.availableBal = availableBal;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public double getOpeningBal() {
		return openingBal;
	}
	public void setOpeningBal(double openingBal) {
		this.openingBal = openingBal;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Account(long accountNo, LocalDateTime createdDate, double openingBal, User user,String accountType,double availableBal) {
		super();
		this.accountNo = accountNo;
		this.createdDate = createdDate;
		this.openingBal = openingBal;
		this.user = user;
		this.accountType=accountType;
		this.availableBal=availableBal;
	}
	public Account()
	{
		
	}
	
	
}
