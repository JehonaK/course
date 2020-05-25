package com.course.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "course")
public class Course extends BaseEntity<String>{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacherId;

    @Column(name = "subject_id")
    private String subjectId;

    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomActivity> customActivities;

    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ForumPost> forumPosts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_student",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private List<User> students;

    public Course(String id, String name, String description, User teacherId, String subjectId) {
        super(id);
        this.name = name;
        this.description = description;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(User teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<CustomActivity> getCustomActivities() {
        return customActivities;
    }

    public void setCustomActivities(List<CustomActivity> customActivities) {
        this.customActivities = customActivities;
    }

    public List<ForumPost> getForumPosts() {
        return forumPosts;
    }

    public void setForumPosts(List<ForumPost> forumPosts) {
        this.forumPosts = forumPosts;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
}
