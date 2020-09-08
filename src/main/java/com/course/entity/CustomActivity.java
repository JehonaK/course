package com.course.entity;

import com.course.annotations.ApiEntity;
import com.course.type.GradeSystem;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "custom_activity")
@ApiEntity
public class CustomActivity extends BaseEntity<String>{

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courseId;

    @Column(name = "grade_system")
    private GradeSystem gradeSystem;

    @Column(name = "has_evaluation")
    private boolean hasEvaluation;

    @Column(name = "has_file_upload")
    private boolean hasFileUpload;

    @Column(name = "has_deadline")
    private boolean hasDeadline;

    @Column(name = "name")
    private String name;

    public CustomActivity() {}

    public CustomActivity(Course courseId, GradeSystem gradeSystem, boolean hasEvaluation, boolean hasFileUpload, boolean hasDeadline, String name) {
        this.courseId = courseId;
        this.gradeSystem = gradeSystem;
        this.hasEvaluation = hasEvaluation;
        this.hasFileUpload = hasFileUpload;
        this.hasDeadline = hasDeadline;
        this.name = name;
    }

    public CustomActivity(String id, Course courseId, GradeSystem gradeSystem, boolean hasEvaluation, boolean hasFileUpload,
                          boolean hasDeadline, String name) {
        super(id);
        this.courseId = courseId;
        this.gradeSystem = gradeSystem;
        this.hasEvaluation = hasEvaluation;
        this.hasFileUpload = hasFileUpload;
        this.hasDeadline = hasDeadline;
        this.name = name;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public GradeSystem getGradeSystem() {
        return gradeSystem;
    }

    public void setGradeSystem(GradeSystem gradeSystem) {
        this.gradeSystem = gradeSystem;
    }

    public boolean isHasEvaluation() {
        return hasEvaluation;
    }

    public void setHasEvaluation(boolean hasEvaluation) {
        this.hasEvaluation = hasEvaluation;
    }

    public boolean isHasFileUpload() {
        return hasFileUpload;
    }

    public void setHasFileUpload(boolean hasFileUpload) {
        this.hasFileUpload = hasFileUpload;
    }

    public boolean isHasDeadline() {
        return hasDeadline;
    }

    public void setHasDeadline(boolean hasDeadline) {
        this.hasDeadline = hasDeadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
