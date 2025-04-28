package com.trust.rms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trust.rms.models.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	

}
