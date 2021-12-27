package com.operations.bank.service;

import com.operations.bank.request.FundtransferRquest;
import com.operations.bank.response.Response;

public interface FundTransferService {
	
	public Response fundtransfer(FundtransferRquest request);

}
