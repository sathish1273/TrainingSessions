package com.operations.bank.service;

import java.time.LocalDateTime;

import com.operations.bank.response.Response;

public interface StatementService {

	public Response getTransactions(LocalDateTime startDate,LocalDateTime endDate,long accountNumber);
}
  