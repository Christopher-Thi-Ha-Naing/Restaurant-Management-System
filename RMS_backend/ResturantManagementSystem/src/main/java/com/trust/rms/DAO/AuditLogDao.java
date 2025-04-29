package com.trust.rms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trust.rms.models.AuditLog;

public interface AuditLogDao extends JpaRepository<AuditLog, Long> {

}
