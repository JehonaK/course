package com.course.entity;

import com.course.type.GradeSystem;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "custom_activity")
public class CustomActivityEntity extends BaseEntity<String>{

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntityId;

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

    public CustomActivityEntity() {}

    public CustomActivityEntity(CourseEntity courseEntityId, GradeSystem gradeSystem, boolean hasEvaluation, boolean hasFileUpload, boolean hasDeadline, String name) {
        this.courseEntityId = courseEntityId;
        this.gradeSystem = gradeSystem;
        this.hasEvaluation = hasEvaluation;
        this.hasFileUpload = hasFileUpload;
        this.hasDeadline = hasDeadline;
        this.name = name;
    }

    public CustomActivityEntity(String id, CourseEntity courseEntityId, GradeSystem gradeSystem, boolean hasEvaluation, boolean hasFileUpload,
                                boolean hasDeadline, String name) {
        super(id);
        this.courseEntityId = courseEntityId;
        this.gradeSystem = gradeSystem;
        this.hasEvaluation = hasEvaluation;
        this.hasFileUpload = hasFileUpload;
        this.hasDeadline = hasDeadline;
        this.name = name;
    }

    public CourseEntity getCourseEntityId() {
        return courseEntityId;
    }

    public void setCourseEntityId(CourseEntity courseEntityId) {
        this.courseEntityId = courseEntityId;
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
