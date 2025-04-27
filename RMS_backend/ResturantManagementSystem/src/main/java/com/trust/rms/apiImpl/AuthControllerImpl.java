package com.trust.rms.apiImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trust.rms.JWT.AuthRequest;
import com.trust.rms.JWT.CustomerUsersDetailService;
import com.trust.rms.JWT.JwtUtils;
import com.trust.rms.api.AuthController;


@RestController
@RequestMapping("/api/auth")
public class AuthControllerImpl implements AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private CustomerUsersDetailService customerUsersDetailService;
    
    

	@Override
	public ResponseEntity<Map<String, String>> login(AuthRequest authRequest) {
		
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
	        response.put("username", user.getName()); // or getEmail() if you prefer
	        response.put("role", user.getRole().getName()); // Assuming Role is a separate entity
	        
	        return ResponseEntity.ok(response);
	    } else {
	        throw new UsernameNotFoundException("Invalid credentials");
	    }
		}
        SecurityContextHolder.getContext().setAuthentication(authentication);
        com.trust.rms.models.User user = customerUsersDetailService.getUserDetail();
        String token = jwtUtils.generateToken(user.getEmail(), user.getRole().getName());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", user.getName());
        response.put("role", user.getRole().getName());
        
        
        return ResponseEntity.ok(response);
    }

}
