package com.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "file_upload")
public class FileUpload extends BaseEntity<String>{

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluationId;

    @Column(name = "upload_time")
    private Timestamp uploadTime;

    public FileUpload(String id, Evaluation evaluationId, Timestamp uploadTime) {
        super(id);
        this.evaluationId = evaluationId;
        this.uploadTime = uploadTime;
    }

    public Evaluation getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Evaluation evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

}
