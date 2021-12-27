package com.operations.bank.service;

import com.operations.bank.request.UserRequest;
import com.operations.bank.response.Response;

public interface RegistrationService {
	
	public Response addUser(UserRequest request);

}
