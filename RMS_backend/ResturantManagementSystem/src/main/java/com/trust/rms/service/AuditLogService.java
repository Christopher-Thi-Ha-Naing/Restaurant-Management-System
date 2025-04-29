package com.trust.rms.service;

public interface AuditLogService {
	
	public void log(String username, String entityName, String entityId, String action, String changes);

}
