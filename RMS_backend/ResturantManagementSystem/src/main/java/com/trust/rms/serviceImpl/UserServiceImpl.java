package com.trust.rms.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trust.rms.DAO.RoleDao;
import com.trust.rms.DAO.UserDao;
import com.trust.rms.DTO.UserDto;
import com.trust.rms.constants.UserStatus;
import com.trust.rms.exception.AlreadyExistException;
import com.trust.rms.exception.FieldRequiredException;
import com.trust.rms.exception.ResourceNotFoundException;
import com.trust.rms.models.Role;
import com.trust.rms.models.User;
import com.trust.rms.service.UserService;
import com.trust.rms.utils.Auditable;
import com.trust.rms.utils.RmsUtils;
import com.trust.rms.utils.RmsValidation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	
	private RmsValidation validation;
	private UserDao userDao;
	private RoleDao roleDao;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(final RmsValidation validation, final UserDao userDao, final RoleDao roleDao,
			final PasswordEncoder passwordEncoder) {
		this.validation = validation;
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
				
	}

	@Override
	@Auditable(action = "CREATE")
	public User signUp(Map<String, String> request) 
			throws FieldRequiredException, AlreadyExistException {
		
		log.info("User Service Sign Up request received : {}",request);
		validation.UserSignUpValidation(request);
		User user = userDao.findByEmail(request.get("email"));
		
		if(Objects.isNull(user)) {
			user = RmsUtils.mapToEntity(request, User.class);
			Role defaultRole = roleDao.findByName("USER")
				    .orElseThrow(() -> new FieldRequiredException("Default role USER not found"));
				user.setRole(defaultRole);
			user.setPassword(passwordEncoder.encode(request.get("password")));
			return userDao.save(user);
		}else {
			throw new AlreadyExistException("Email");
		}
	}

	@Override
	public String getRoleByEmail(String email) {
		log.info("User Service Role Finding By Email request received : {}",email);
		User user = userDao.findByEmail(email);
		if (user != null && user.getRole() != null) {
            return user.getRole().getName(); 
        }
		return null;
	}

	@Override
	public User findByEmail(String email) {
		log.info("User Service User Finding By Email request received : {}",email);
		return userDao.findByEmail(email);
	}

	@Override
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<User> users = userDao.findAll();
		List<UserDto> userDtos = users.stream()
		            .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(), 
		                                     user.getPassword(), user.getPhone(), user.getAddress(), 
		                                     user.getRole().getId(), user.getRole().getName(), 
		                                     user.getStatus().toString()))
		            .collect(Collectors.toList());
		 return ResponseEntity.ok(userDtos);
		
	}

	@Override
	@Transactional
	@Auditable(action = "UPDATE")
	public User updateUser(Map<String, String> request) {
		Integer id = Integer.parseInt(request.get("id")); // make sure 'id' key exists

	    Optional<User> optionalUser = userDao.findById(id);
	    if (!optionalUser.isPresent()) {
	        throw new ResourceNotFoundException("User not found with id: " + id);
	    }

	    User user = optionalUser.get();

	    if (request.containsKey("name")) {
	        user.setName(request.get("name"));
	    }
	    if (request.containsKey("email")) {
	        user.setEmail(request.get("email"));
	    }
	    if (request.containsKey("phone")) {
	        user.setPhone(request.get("phone"));
	    }
	    if (request.containsKey("address")) {
	        user.setAddress(request.get("address"));
	    }
	    if (request.containsKey("status")) {
	        user.setStatus(UserStatus.valueOf(request.get("status").toUpperCase()));
	    }
	    if (request.containsKey("roleId")) {
	        Integer roleId = Integer.parseInt(request.get("roleId"));
	        Role role = roleDao.findById(roleId)
	                     .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
	        user.setRole(role);
	    }

	    userDao.save(user);

	    return userDao.save(user);
	}

}
