package com.ddingo.photoservice.domain.config;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "regDt" , updatable = false)
    private LocalDateTime regDt;

    @LastModifiedDate
    @Column(name = "modDt" , insertable = false)
    private LocalDateTime modDt;

    public LocalDateTime getCreatedDate() {
        return regDt;
    }

    public LocalDateTime getModifiedDate() {
        return modDt;
    }
}
