package com.trust.rms.exception;

public class UnauthorizedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10011L;

	public UnauthorizedException(String message) {
        super(message);
    }

}
