package com.operations.bank.enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long userId;
	private String fname;
	private String lname;
	private int age;
	@Column
	private long uid;
	private long phoneNo;
	private String email;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getuID() {
		return uid;
	}
	public void setuID(long uID) {
		this.uid = uID;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User(String fname, String lname, int age, long uID, long phoneNo, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.uid = uID;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	public User() {
		
	}
	
}
