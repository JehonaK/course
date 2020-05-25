package com.course.entity;

import com.course.type.GradeSystem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "evaluation")
public class Evaluation extends BaseEntity<String> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private Activity activityId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User studentId;

    @OneToOne(mappedBy = "evaluationId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FileUpload fileUpload;

    @Column(name = "grade")
    private String grade;

    @Column(name = "grade_system")
    private GradeSystem gradeSystem;

    @Column(name = "feedback")
    private String feedback;

    public Evaluation(String id, String grade, GradeSystem gradeSystem, Activity activityId, User studentId) {
        super(id);
        this.grade = grade;
        this.gradeSystem = gradeSystem;
        this.activityId = activityId;
        this.studentId = studentId;
    }

    public Evaluation(String id, String grade, GradeSystem gradeSystem, Activity activityId, User studentId, String feedback) {
        super(id);
        this.grade = grade;
        this.gradeSystem = gradeSystem;
        this.activityId = activityId;
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

    public Activity getActivityId() {
        return activityId;
    }

    public void setActivityId(Activity activityId) {
        this.activityId = activityId;
    }

    public User getStudentId() {
        return studentId;
    }

    public void setStudentId(User studentId) {
        this.studentId = studentId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }
}
