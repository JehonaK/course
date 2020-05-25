package com.course.entity;

import com.course.type.GradeSystem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "activity")
public class Activity extends BaseEntity<String>{

    @Column(name = "name")
    private String name;

    @Column(name = "deadline")
    private Timestamp deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course courseId;

    @OneToMany(mappedBy = "activityId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Evaluation> evaluations;

    public Activity(String id, String name, Timestamp deadline, Course courseId, String comment) {
        super(id, comment);
        this.name = name;
        this.deadline = deadline;
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
}
