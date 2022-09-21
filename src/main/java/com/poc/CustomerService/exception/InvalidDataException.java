package com.poc.CustomerService.exception;

import lombok.Getter;

@Getter
public class InvalidDataException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public InvalidDataException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}
	
}
