package com.operations.bank.service;

import com.operations.bank.response.Response;

public interface StatementService {

	public Response getTransactions(String date);
}
