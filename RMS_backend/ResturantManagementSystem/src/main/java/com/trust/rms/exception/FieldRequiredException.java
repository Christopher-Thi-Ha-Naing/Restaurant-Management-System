package com.trust.rms.exception;

import org.springframework.http.HttpStatus;

public class FieldRequiredException extends BaseException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10010L;

	public FieldRequiredException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
