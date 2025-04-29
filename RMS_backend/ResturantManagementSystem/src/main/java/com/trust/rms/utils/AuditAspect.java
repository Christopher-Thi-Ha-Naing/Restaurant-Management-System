package com.trust.rms.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trust.rms.service.AuditLogService;

@Aspect
@Component
public class AuditAspect {
	
	@Autowired
	private AuditLogService auditLogService;
	
	@Pointcut("@annotation(com.trust.rms.utils.Auditable)")
    public void auditableMethods() {}
	
	@AfterReturning(pointcut = "auditableMethods()", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        String username = getCurrentUsername();
        String methodName = joinPoint.getSignature().getName();

        Auditable auditableAnnotation = getAuditableAnnotation(joinPoint);

        String action = auditableAnnotation != null && !auditableAnnotation.action().isEmpty() ? 
            auditableAnnotation.action() : determineAction(methodName);

        Object entity = joinPoint.getArgs()[0];
        String entityName = methodName;
        String entityId = getEntityId(result, entity);
        String changes = getChanges(entity);

        auditLogService.log(username, entityName, entityId, action, changes);
    }
	
	private Auditable getAuditableAnnotation(JoinPoint joinPoint) {
        try {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            return method.getAnnotation(Auditable.class);
        } catch (Exception e) {
            return null;
        }
    }
	
	private String getCurrentUsername() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
	        return auth.getName();
	    }
	    return "SYSTEM";
	}
	
	private String determineAction(String methodName) {
        if (methodName.startsWith("save")) return "INSERT";
        if (methodName.startsWith("update")) return "UPDATE";
        if (methodName.startsWith("delete")) return "DELETE";
        return "UNKNOWN";
    }
	
	private String getEntityId(Object result, Object entity) {
	    try {
	        Object target = (result != null) ? result : entity;
	        Field idField = target.getClass().getDeclaredField("id");
	        idField.setAccessible(true);
	        Object id = idField.get(target);
	        return (id != null) ? id.toString() : "N/A";
	    } catch (Exception e) {
	        return "N/A";
	    }
	}
	 
	 private String getChanges(Object entity) {
	        try {
	            return new ObjectMapper().writeValueAsString(entity);
	        } catch (JsonProcessingException e) {
	            return "Could not serialize changes.";
	        }
	    }



}
