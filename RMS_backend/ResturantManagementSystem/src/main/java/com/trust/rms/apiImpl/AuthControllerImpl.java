package com.trust.rms.apiImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trust.rms.JWT.AuthRequest;
import com.trust.rms.JWT.CustomerUsersDetailService;
import com.trust.rms.JWT.JwtUtils;
import com.trust.rms.api.AuthController;
import com.trust.rms.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthControllerImpl implements AuthController {
	
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserService userService;
  
	public AuthControllerImpl(final AuthenticationManager authenticationManager, final JwtUtils jwtUtils,
			final CustomerUsersDetailService customerUsersDetailService, final UserService userService) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.userService = userService;
	}

	@PostMapping("/login")
	@Override
	public ResponseEntity<Map<String, String>> login(AuthRequest authRequest) {
		log.info("Authentication API request received : {}",authRequest);
		
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

		if (authentication.isAuthenticated()) {
	        String token = jwtUtils.generateToken(authRequest.getEmail(), userService.getRoleByEmail(authRequest.getEmail()));
	        
	        // Get user details
	        com.trust.rms.models.User user = userService.findByEmail(authRequest.getEmail());
	        
	        // Prepare response
	        Map<String, String> response = new HashMap<>();
	        response.put("token", token);
	        response.put("username", user.getName());
	        response.put("role", user.getRole().getName());
	        
	        return ResponseEntity.ok(response);
	    } else {
	        throw new UsernameNotFoundException("Invalid credentials");
	    }

   }

}
