package com.operations.bank.requestValidator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.operations.bank.dto.BusinessMessage;
import com.operations.bank.dto.StatusEnum;
import com.operations.bank.response.Response;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Response response=new Response();
		List<BusinessMessage> list= new ArrayList<>();
        ex.getBindingResult()
        		.getAllErrors().forEach((error)->{
        			String fieldname=((FieldError)error).getField();
        			String message=error.getDefaultMessage();
        			list.add(new BusinessMessage(fieldname+" : "+message));
        		});
        if(!list.isEmpty()) {
			response.setStatus(StatusEnum.FAIL);
			response.setBusinessMessage(list);
		}

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}