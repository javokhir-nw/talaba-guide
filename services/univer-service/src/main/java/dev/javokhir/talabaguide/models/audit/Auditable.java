package dev.javokhir.talabaguide.models.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    @CreatedBy
    @Column(name = "created_who",updatable = false)
    private String createdWho;

    @CreatedDate
    @Column(name = "created_date",updatable = false)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_who")
    private String lastModifiedWho;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date updatedDate;
}
