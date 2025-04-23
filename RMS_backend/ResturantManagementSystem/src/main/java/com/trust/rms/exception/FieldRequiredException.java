package com.trust.rms.exception;

import org.springframework.http.HttpStatus;

public class FieldRequiredException extends RuntimeException {
	
	private final HttpStatus status;
	
	public FieldRequiredException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

}
