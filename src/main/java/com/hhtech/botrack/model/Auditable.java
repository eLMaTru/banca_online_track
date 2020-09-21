package com.hhtech.botrack.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Calendar;

/**
 * The type Auditable.
 */
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@EntityListeners({ AuditingEntityListener.class })
@MappedSuperclass
public class Auditable<U> {

    /**
     * createdDate
     */
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    /**
     * lastModifiedDate
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    private Calendar lastModifiedDate;

    /**
     * createBy
     */
    @CreatedBy
    @Column(name = "created_by", length = 20)
    private U createdBy;

    /**
     * lastModifiedBy
     */
    @LastModifiedBy
    @Column(name = "last_modified_by", length = 20)
    private U lastModifiedBy;

}
