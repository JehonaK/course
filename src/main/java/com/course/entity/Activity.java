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
public class Activity extends BaseEntity<String> implements Comparable<Activity>{

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
    private Course courseId;

    @JsonIgnore
    @OneToMany(mappedBy = "activityId", fetch = FetchType.LAZY)
    private List<Evaluation> evaluations;

    @JsonIgnore
    @OneToMany(mappedBy = "activityId", fetch = FetchType.LAZY)
    private List<FileUpload> fileUploads;

    public Activity() {}

    public Activity(String id, String name, Timestamp deadline, String description, boolean hasFileUpload,
                    GradeSystem gradeSystem, String grade, Course courseId) {
        super(id);
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.hasFileUpload = hasFileUpload;
        this.gradeSystem = gradeSystem;
        this.grade = grade;
        this.courseId = courseId;
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

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
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

    public List<FileUpload> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(List<FileUpload> fileUploads) {
        this.fileUploads = fileUploads;
    }


    @Override
    public int compareTo(Activity activity) {
        return getCreateDateTime().compareTo(activity.getCreateDateTime());
    }
}
