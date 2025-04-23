package com.trust.rms.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RmsUtils {
	
	private RmsUtils() {
		
	}
	
	public static ResponseEntity<String> getResponseEntity(String response, HttpStatus httpStatus){
		return new ResponseEntity<String>("{\"message \": \""+response+"\"}",httpStatus);
	}

}
