package com.trust.rms.utils;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trust.rms.exception.FieldRequiredException;

@Service
public class RmsValidation {
	
	HttpStatus badRequest = HttpStatus.BAD_REQUEST;
	
	public void UserSignUpValidation(Map<String,String> request) {
		
		if (!request.containsKey("name") || request.get("name").isBlank()) {
	        throw new FieldRequiredException("Name is required", badRequest);
	    }

	    if (!request.containsKey("email") || request.get("email").isBlank()) {
	        throw new FieldRequiredException("Email is required", badRequest);
	    }

	    if (!request.containsKey("phone") || request.get("phone").isBlank()) {
	        throw new FieldRequiredException("Phone number is required", badRequest);
	    }

	    if (!request.containsKey("password") || request.get("password").isBlank()) {
	        throw new FieldRequiredException("Password is required", badRequest);
	    }

	    // Email format validation
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    if (!request.get("email").matches(emailRegex)) {
	        throw new FieldRequiredException("Invalid email format", badRequest);
	    }

	    // Phone number format validation
	    String phoneRegex = "^[0-9]{10,15}$";
	    if (!request.get("phone").matches(phoneRegex)) {
	        throw new FieldRequiredException("Invalid phone number format", badRequest);
	    }

	    // Password validation
	    String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";
	    if (!request.get("password").matches(passwordRegex)) {
	        throw new FieldRequiredException("Password must be at least 8 characters long, include one uppercase letter, one number, and one special character", badRequest);
	    }
		
	}

}
