package com.operations.bank.service;

import java.time.LocalDate;

import com.operations.bank.response.Response;

public interface StatementService {

	public Response getTransactions(LocalDate startDate,LocalDate endDate);
}
