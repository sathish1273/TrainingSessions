package com.operations.bank.service;

import java.util.List;

import com.operations.bank.enity.Transactions;

public interface StatementService {

	public List<Transactions> getTransactions(String date);
}
