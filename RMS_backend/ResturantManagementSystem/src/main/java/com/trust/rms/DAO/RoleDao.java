package com.trust.rms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.trust.rms.models.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	

}
