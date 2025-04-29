package com.trust.rms.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trust.rms.DTO.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(path="/user")
@Tag(name = "User API", description = "Operations related to users")
public interface UserController {
	
	@PostMapping(path= "/signup")
	public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String,String> request);
	
	
	@Operation(summary = "Update user information",description = "Updates the user details. Requires Authorization header.")
	@PostMapping(path="/update")
	public ResponseEntity<String> updateUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(
	        description = "Map containing fields to update, such as id, name, email",
	        required = true
	    ) @RequestBody(required = true) Map<String,String> request);
	
	
	@Operation(summary = "Get all users", description = "Returns a list of all registered users")
	@GetMapping(path="/getAllUser")
	public ResponseEntity<List<UserDto>> getAllUser();

}
