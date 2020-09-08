package com.course.entity;

import com.course.annotations.ApiEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "file_upload")
@ApiEntity
public class FileUpload extends BaseEntity<String> {

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activityId;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lessonId;

    @Column(name = "upload_time")
    private Timestamp uploadTime;

    @Column(name = "original_name")
    private String originalName;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

    public FileUpload() {}

    public FileUpload(Activity activityId, Timestamp uploadTime) {
        this.activityId = activityId;
        this.uploadTime = uploadTime;
    }

    public FileUpload(Lesson lessonId, Timestamp uploadTime, String originalName) {
        this.lessonId = lessonId;
        this.uploadTime = uploadTime;
        this.originalName = originalName;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Activity getActivityId() {
        return activityId;
    }

    public void setActivityId(Activity activityId) {
        this.activityId = activityId;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    public void setLessonId(Lesson lessonId) {
        this.lessonId = lessonId;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public User getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(User uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

}
