package com.trust.rms.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trust.rms.JWT.AuthRequest;

@RequestMapping("/api/auth")
public interface AuthController {
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest);

}
