package com.trust.rms.apiImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.trust.rms.api.UserController;
import com.trust.rms.constants.RmsConstants;
import com.trust.rms.exception.AlreadyExistException;
import com.trust.rms.exception.FieldRequiredException;
import com.trust.rms.service.UserService;
import com.trust.rms.utils.RmsUtils;

@RestController
public class UserApiImpl implements UserController {
	
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> request) {
		try {
			return userService.signUp(request);	
		}catch(FieldRequiredException e) {
			return RmsUtils.getResponseEntity(e.getMessage(), e.getStatus());
		}catch(AlreadyExistException e) {
			return RmsUtils.getResponseEntity(e.getMessage(), e.getStatus());
		}catch(Exception e){
			return RmsUtils.getResponseEntity(RmsConstants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
