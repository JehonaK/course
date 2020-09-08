package com.course.entity;

import com.course.annotations.ApiEntity;
import com.course.type.RecordStatus;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@ApiEntity
public abstract class BaseEntity<T> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "NVARCHAR(36)")
    private T id;

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus = RecordStatus.ACTIVE;

    @CreationTimestamp
    private Timestamp createDateTime;

    @UpdateTimestamp
    private Timestamp updateDateTime;

    private Timestamp deletedDateTime;

    private String createdByUserId;

    private String updatedByUserId;

    @Column(length = 1000)
    private String note;

    public BaseEntity() {

    }

    public BaseEntity(T id) {
        this.id = id;
    }

    public BaseEntity(T id, String note) {
        this.id = id;
        this.note = note;
    }

    public BaseEntity(T id, Timestamp createDateTime) {
        this.id = id;
        this.createDateTime = createDateTime;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
}
