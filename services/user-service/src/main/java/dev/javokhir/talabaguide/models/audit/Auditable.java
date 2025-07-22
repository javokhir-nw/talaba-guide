package dev.javokhir.talabaguide.models.audit;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class Auditable<U> {
    @CreatedBy
    @Column(name = "created_who",updatable = false)
    private String createdWho;

    @CreatedDate
    @Column(name = "created_date",updatable = false)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_who",updatable = false)
    private String lastModifiedWho;

    @LastModifiedDate
    @Column(name = "last_modified_date",updatable = false)
    private Date updatedDate;
}
