package com.trust.rms.models;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "audit_logs")
public class AuditLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private Long id;

	@Column(name="username")
    private String username;

	@Column(name="entityName")
    private String entityName;

	@Column(name="action")
    private String action; 

	@Column(name="entityId")
    private String entityId;

    @Lob
    @Column(name="changes")
    private String changes;

    @Column(name="timestamp")
    private LocalDateTime timestamp;
    
    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now(); 
        }
    }
    
    public AuditLog(String username, String entityName, String entityId, String action, String changes) {
        this.username = username;
        this.entityName = entityName;
        this.entityId = entityId;
        this.action = action;
        this.changes = changes;
        this.timestamp = LocalDateTime.now();
    }


}
