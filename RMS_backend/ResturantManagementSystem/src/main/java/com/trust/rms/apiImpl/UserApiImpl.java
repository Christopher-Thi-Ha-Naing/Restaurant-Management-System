package com.trust.rms.apiImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.trust.rms.api.UserApi;
import com.trust.rms.constants.RmsConstants;
import com.trust.rms.service.UserService;
import com.trust.rms.utils.RmsUtils;

@RestController
public class UserApiImpl implements UserApi {
	
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> request) {
		try {
			return userService.signUp(request);	
		}catch(Exception e){
			return RmsUtils.getResponseEntity(RmsConstants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
