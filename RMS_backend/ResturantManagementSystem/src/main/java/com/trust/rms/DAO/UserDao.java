package com.trust.rms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.trust.rms.models.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByEmail(@Param("email")String email);
}
