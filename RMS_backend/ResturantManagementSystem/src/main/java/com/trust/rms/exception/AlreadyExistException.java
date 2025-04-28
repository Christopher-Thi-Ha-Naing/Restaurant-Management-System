package com.trust.rms.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10001L;

	public AlreadyExistException(String message) {
        super(message, HttpStatus.CONFLICT);
    }

}
