package com.intern.core.service.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	
	public DataNotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
