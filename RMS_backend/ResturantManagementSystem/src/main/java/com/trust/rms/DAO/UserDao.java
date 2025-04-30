package com.trust.rms.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.trust.rms.models.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByEmail(@Param("email")String email);
	
	List<User> findAll();
	
	List<User> findAllByRole_Id(Integer roleId);
}
