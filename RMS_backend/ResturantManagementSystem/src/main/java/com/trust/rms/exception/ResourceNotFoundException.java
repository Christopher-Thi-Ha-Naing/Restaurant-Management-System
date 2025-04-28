package com.trust.rms.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10100L;

	public ResourceNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}

}
