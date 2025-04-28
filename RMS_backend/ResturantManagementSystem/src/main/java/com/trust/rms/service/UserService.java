package com.trust.rms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.trust.rms.DTO.UserDto;
import com.trust.rms.exception.AlreadyExistException;
import com.trust.rms.exception.FieldRequiredException;
import com.trust.rms.models.User;

public interface UserService {

	ResponseEntity<String> signUp(Map<String, String> request) throws FieldRequiredException, AlreadyExistException;

	String getRoleByEmail(String email);

	User findByEmail(String email);
	
	ResponseEntity<List<UserDto>> getAllUser();
	
	public ResponseEntity<String> updateUser(Map<String, String> request);

}
