package com.trust.rms.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.trust.rms.exception.AlreadyExistException;
import com.trust.rms.exception.FieldRequiredException;

public interface UserService {

	ResponseEntity<String> signUp(Map<String, String> request) throws FieldRequiredException, AlreadyExistException;

}
