package com.course.entity;

import com.course.type.GradeSystem;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "evaluation")
public class EvaluationEntity extends BaseEntity<String> {

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ActivityEntity activityEntityId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private UserEntity studentId;

    @Column(name = "grade")
    private String grade;

    @Column(name = "grade_system")
    private GradeSystem gradeSystem;

    @Column(name = "feedback")
    private String feedback;

//    @OneToOne(mappedBy = "evaluationEntityId", fetch = FetchType.EAGER)
//    private FileUploadEntity fileUpload;

    public EvaluationEntity() {}

    public EvaluationEntity(String id, String grade, GradeSystem gradeSystem, ActivityEntity activityEntityId, UserEntity studentId) {
        super(id);
        this.grade = grade;
        this.gradeSystem = gradeSystem;
        this.activityEntityId = activityEntityId;
        this.studentId = studentId;
    }

    public EvaluationEntity(String id, String grade, GradeSystem gradeSystem, ActivityEntity activityEntityId, UserEntity studentId, String feedback) {
        super(id);
        this.grade = grade;
        this.gradeSystem = gradeSystem;
        this.activityEntityId = activityEntityId;
        this.studentId = studentId;
        this.feedback = feedback;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public GradeSystem getGradeSystem() {
        return gradeSystem;
    }

    public void setGradeSystem(GradeSystem gradeSystem) {
        this.gradeSystem = gradeSystem;
    }

    public ActivityEntity getActivityEntityId() {
        return activityEntityId;
    }

    public void setActivityEntityId(ActivityEntity activityEntityId) {
        this.activityEntityId = activityEntityId;
    }

    public UserEntity getStudentId() {
        return studentId;
    }

    public void setStudentId(UserEntity studentId) {
        this.studentId = studentId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
