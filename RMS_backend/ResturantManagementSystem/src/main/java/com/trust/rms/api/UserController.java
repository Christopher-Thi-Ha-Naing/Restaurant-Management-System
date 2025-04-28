package com.trust.rms.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trust.rms.DTO.UserDto;

@RequestMapping(path="/user")
public interface UserController {
	
	@PostMapping(path= "/signup")
	public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String,String> request);
	
	@PostMapping(path="/update")
	public ResponseEntity<String> updateUser(@RequestBody(required = true) Map<String,String> request);
	
	@GetMapping(path="/getAllUser")
	public ResponseEntity<List<UserDto>> getAllUser();

}
