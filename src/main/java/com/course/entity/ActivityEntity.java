package com.course.entity;

import com.course.type.GradeSystem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "activity")
public class ActivityEntity extends BaseEntity<String> implements Comparable<ActivityEntity>{

    @Column(name = "name")
    private String name;

    @Column(name = "deadline")
    private Timestamp deadline;

    @Column(name = "description")
    private String description;

    @Column(columnDefinition = "boolean default 0")
    private boolean hasFileUpload = false;

    @Column(name = "grade_system")
    private GradeSystem gradeSystem;

    @Column(name = "grade")
    private String grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntityId;

    @JsonIgnore
    @OneToMany(mappedBy = "activityEntityId", fetch = FetchType.LAZY)
    private List<EvaluationEntity> evaluationEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "activityEntityId", fetch = FetchType.LAZY)
    private List<FileUploadEntity> fileUploadEntities;

    public ActivityEntity() {}

    public ActivityEntity(String id, String name, Timestamp deadline, String description, boolean hasFileUpload,
                          GradeSystem gradeSystem, String grade, CourseEntity courseEntityId) {
        super(id);
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.hasFileUpload = hasFileUpload;
        this.gradeSystem = gradeSystem;
        this.grade = grade;
        this.courseEntityId = courseEntityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public CourseEntity getCourseEntityId() {
        return courseEntityId;
    }

    public void setCourseEntityId(CourseEntity courseEntityId) {
        this.courseEntityId = courseEntityId;
    }

    public List<EvaluationEntity> getEvaluationEntities() {
        return evaluationEntities;
    }

    public void setEvaluationEntities(List<EvaluationEntity> evaluationEntities) {
        this.evaluationEntities = evaluationEntities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasFileUpload() {
        return hasFileUpload;
    }

    public void setHasFileUpload(boolean hasFileUpload) {
        this.hasFileUpload = hasFileUpload;
    }

    public GradeSystem getGradeSystem() {
        return gradeSystem;
    }

    public void setGradeSystem(GradeSystem gradeSystem) {
        this.gradeSystem = gradeSystem;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<FileUploadEntity> getFileUploadEntities() {
        return fileUploadEntities;
    }

    public void setFileUploadEntities(List<FileUploadEntity> fileUploadEntities) {
        this.fileUploadEntities = fileUploadEntities;
    }


    @Override
    public int compareTo(ActivityEntity activityEntity) {
        return getCreateDateTime().compareTo(activityEntity.getCreateDateTime());
    }
}
