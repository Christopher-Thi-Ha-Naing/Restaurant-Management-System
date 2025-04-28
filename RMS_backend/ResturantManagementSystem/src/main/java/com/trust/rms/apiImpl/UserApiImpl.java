package com.trust.rms.apiImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.trust.rms.DTO.UserDto;
import com.trust.rms.JWT.JwtUtils;
import com.trust.rms.api.UserController;
import com.trust.rms.exception.UnauthorizedException;
import com.trust.rms.service.UserService;

@RestController
public class UserApiImpl implements UserController {
	
	@Autowired
	private UserService userService;
	

	@Override
	public ResponseEntity<String> signUp(Map<String, String> request) {
		return userService.signUp(request);
	}

	@Override
	public ResponseEntity<List<UserDto>> getAllUser() {
	    if (JwtUtils.isAdmin()) {
	        return userService.getAllUser();
	    } else {
	        throw new UnauthorizedException("Access Denied: Only Admins can view users.");
	    }
	}

	@Override
	public ResponseEntity<String> updateUser(Map<String, String> request) {
		if (JwtUtils.isAdmin()) {
			return userService.updateUser(request);
		} else {
	        throw new UnauthorizedException("Access Denied: Only Admins can view users.");
	    }
	}

}
