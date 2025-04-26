package com.trust.rms.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trust.rms.DAO.UserDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerUsersDetailService implements UserDetailsService{
	
	@Autowired
	UserDao userDao;
	
	private com.trust.rms.models.User userDetail;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Inside loadUserByUserName {}", username);
		userDetail = userDao.findByEmail(username);
		if(!Objects.isNull(userDetail)) {
			return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
	
	public com.trust.rms.models.User getUserDetail(){
		return userDetail;
	}

}
