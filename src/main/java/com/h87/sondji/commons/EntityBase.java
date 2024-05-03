package com.h87.sondji.commons;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "c_id")
    private UUID id;

    @Version
    @Column(name = "c_version")
    private int version;

    @Column(name = "c_tenant_id")
    private String tenantId;

    @Column(name = "c_creation_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "c_last_modified_date")
    private LocalDateTime lastModifiedDate;

    @PrePersist
    private void prePersist() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        lastModifiedDate = LocalDateTime.now();
    }
}
