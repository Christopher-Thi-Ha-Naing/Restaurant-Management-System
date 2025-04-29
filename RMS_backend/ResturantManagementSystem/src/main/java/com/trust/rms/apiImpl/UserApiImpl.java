package com.trust.rms.apiImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.trust.rms.DTO.UserDto;
import com.trust.rms.JWT.JwtUtils;
import com.trust.rms.api.UserController;
import com.trust.rms.exception.UnauthorizedException;
import com.trust.rms.service.UserService;
import com.trust.rms.utils.RmsUtils;

@RestController
public class UserApiImpl implements UserController {
	
	@Autowired
	private UserService userService;
	

	@Override
	public ResponseEntity<String> signUp(Map<String, String> request) {
		userService.signUp(request);
		return RmsUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
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
			userService.updateUser(request);
			return ResponseEntity.ok("User updated successfully");
		} else {
	        throw new UnauthorizedException("Access Denied: Only Admins can view users.");
	    }
	}

}
