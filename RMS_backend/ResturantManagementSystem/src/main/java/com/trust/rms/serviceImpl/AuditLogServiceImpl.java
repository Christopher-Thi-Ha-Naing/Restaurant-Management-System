package com.trust.rms.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trust.rms.DAO.AuditLogDao;
import com.trust.rms.models.AuditLog;
import com.trust.rms.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {
	
	@Autowired
	private AuditLogDao auditLogDao;
	
	@Override
	public void log(String username, String entityName, String entityId, String action, String changes) {
        AuditLog log = new AuditLog();
        log.setUsername(username);
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setAction(action);
        log.setChanges(changes);
        log.setTimestamp(LocalDateTime.now());
        auditLogDao.save(log);
    }

}
