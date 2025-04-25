package com.trust.rms.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trust.rms.DAO.RoleDao;
import com.trust.rms.DAO.UserDao;
import com.trust.rms.constants.UserStatus;
import com.trust.rms.exception.AlreadyExistException;
import com.trust.rms.exception.FieldRequiredException;
import com.trust.rms.models.Role;
import com.trust.rms.models.User;
import com.trust.rms.service.UserService;
import com.trust.rms.utils.RmsUtils;
import com.trust.rms.utils.RmsValidation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	
	private RmsValidation validation;
	private UserDao userDao;
	private RoleDao roleDao;
	
	public UserServiceImpl(final RmsValidation validation, final UserDao userDao, final RoleDao roleDao) {
		this.validation = validation;
		this.userDao = userDao;
		this.roleDao = roleDao;
				
	}

	@Override
	public ResponseEntity<String> signUp(Map<String, String> request) 
			throws FieldRequiredException, AlreadyExistException {
		
		log.info("User Sign Up request received: {}",request);
		validation.UserSignUpValidation(request);
		User user = userDao.findByEmail(request.get("email"));
		
		if(Objects.isNull(user)) {
			user = RmsUtils.mapToEntity(request, User.class);
			Integer roleId = Integer.parseInt(request.get("roleId"));
			Role role = roleDao.findById(roleId)
			        .orElseThrow(() -> new FieldRequiredException("Invalid Role ID", HttpStatus.BAD_REQUEST));
			user.setRole(role);
			userDao.save(user);
		}else {
			throw new AlreadyExistException("Email", HttpStatus.BAD_REQUEST);
		}
		return RmsUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
	}

}
