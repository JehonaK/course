package com.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "file_upload")
public class FileUploadEntity extends BaseEntity<String> {

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ActivityEntity activityEntityId;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lessonEntityId;

    @Column(name = "upload_time")
    private Timestamp uploadTime;

    @Column(name = "original_name")
    private String originalName;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private UserEntity uploadedBy;

    public FileUploadEntity() {}

    public FileUploadEntity(ActivityEntity activityEntityId, Timestamp uploadTime) {
        this.activityEntityId = activityEntityId;
        this.uploadTime = uploadTime;
    }

    public FileUploadEntity(LessonEntity lessonEntityId, Timestamp uploadTime, String originalName) {
        this.lessonEntityId = lessonEntityId;
        this.uploadTime = uploadTime;
        this.originalName = originalName;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public ActivityEntity getActivityEntityId() {
        return activityEntityId;
    }

    public void setActivityEntityId(ActivityEntity activityEntityId) {
        this.activityEntityId = activityEntityId;
    }

    public LessonEntity getLessonEntityId() {
        return lessonEntityId;
    }

    public void setLessonEntityId(LessonEntity lessonEntityId) {
        this.lessonEntityId = lessonEntityId;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public UserEntity getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(UserEntity uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

}
