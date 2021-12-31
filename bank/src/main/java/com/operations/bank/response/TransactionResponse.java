package com.operations.bank.response;

public class TransactionResponse {
	
    private long transactionId;
	private long fromAccount;
	private long toAccount;
	private double amount;
	private String comments;
	private String date;
	
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public long getToAccount() {
		return toAccount;
	}
	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public TransactionResponse(long transactionId, long fromAccount, long toAccount, double amount, String comments,
			String date) {
		super();
		this.transactionId = transactionId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.comments = comments;
		this.date = date;
	}
	
	
}
