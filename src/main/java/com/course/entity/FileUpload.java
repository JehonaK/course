package com.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "file_upload")
public class FileUpload extends BaseEntity<String> {

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activityId;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lessonId;

    @Column(name = "upload_time")
    private Timestamp uploadTime;

    public FileUpload() {}

    public FileUpload(Activity activityId, Timestamp uploadTime) {
        this.activityId = activityId;
        this.uploadTime = uploadTime;
    }

    public FileUpload(String id, Activity activityId, Timestamp uploadTime) {
        super(id);
        this.activityId = activityId;
        this.uploadTime = uploadTime;
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

    @Override
    public String toString() {
        return "FileUpload{}";
    }
}
