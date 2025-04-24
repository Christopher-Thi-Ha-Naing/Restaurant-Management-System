package com.trust.rms.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trust.rms.DAO.UserDao;
import com.trust.rms.models.User;
import com.trust.rms.service.UserService;
import com.trust.rms.utils.RmsValidation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RmsValidation validation;
	
	private UserDao userDao;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> request) {
		
		log.info("User Sign Up request received: {}",request);
		validation.UserSignUpValidation(request);
		User user = userDao.findByEmail(request.get("email"));
		return null;
	}

}
