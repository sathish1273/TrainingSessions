package com.operations.bank.enity;

import java.time.LocalDate;
import java.time.LocalTime;

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
	private LocalDate createdDate;
	private LocalTime createdTime;
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
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
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
	public Account(long accountNo, LocalDate createdDate, LocalTime createdTime, double openingBal, User user,String accountType,double availableBal) {
		super();
		this.accountNo = accountNo;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.openingBal = openingBal;
		this.user = user;
		this.accountType=accountType;
		this.availableBal=availableBal;
	}
	public Account()
	{
		
	}
	
	
}
