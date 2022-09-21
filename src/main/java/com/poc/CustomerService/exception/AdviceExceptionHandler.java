package com.poc.CustomerService.exception;

import java.net.SocketTimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.poc.CustomerService.constant.Constants;
import com.poc.CustomerService.dto.Response;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class AdviceExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Response internalServerException(Exception ex, WebRequest request) {
		log.error("Error : {}", ex.getMessage());
		
		String errorCode = (ex instanceof SocketTimeoutException) ? Constants.ERROR_CODE_CONNECTION_TIMEOUT
				: Constants.ERROR_CODE_SERVER_ERROR;
		
		Response response = new Response();
		response.setStatusCode(Constants.STATUS_FAILURE);
		response.setErrorCode(errorCode);
		response.setMessage(ex.getMessage());
		return response;
	}
	
	@ExceptionHandler(value = { InvalidDataException.class })
	@ResponseStatus(HttpStatus.OK)
	public Response invalidDataException(InvalidDataException ex, WebRequest request) {
		
		log.error("Eror : {}", ex.getMessage());
		
		Response response = new Response();
		response.setStatusCode(Constants.STATUS_FAILURE);
		response.setErrorCode(ex.getErrorCode());
		response.setMessage(ex.getMessage());
		
		return response;
	}
}
