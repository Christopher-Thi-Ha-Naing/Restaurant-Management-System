package com.trust.rms.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends RuntimeException {
	
private final HttpStatus status;
	
	public AlreadyExistException(String message, HttpStatus status) {
		super(message+ " already exists");
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

}
