package com.trust.rms.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1000L;
	private final HttpStatus status;
    
    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
