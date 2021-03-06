package com.course.entity;

import com.course.annotations.ApiEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
@ApiEntity
public class Course extends BaseEntity<String>{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "subject_id")
    private String subjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacherId;

    @JsonIgnore
    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Activity> activities;

    @JsonIgnore
    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomActivity> customActivities;

    @JsonIgnore
    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ForumPost> forumPosts;

    @JsonIgnore
    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_student",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private List<User> students;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_class",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "class_id") }
    )
    private List<CourseClass> courseClasses;

    public Course() {}

    public Course(String name, String description, User teacherId, String subjectId) {
        this.name = name;
        this.description = description;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public Course(String id, String name, String description, User teacherId, String subjectId) {
        super(id);
        this.name = name;
        this.description = description;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public Course(String id, String name, String description, User teacherId) {
        super(id);
        this.name = name;
        this.description = description;
        this.teacherId = teacherId;
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

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<CourseClass> getCourseClasses() {
        return courseClasses;
    }

    public void setCourseClasses(List<CourseClass> students) {
        this.courseClasses = courseClasses;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Course) {
            return this.getId().equals(((Course)object).getId());
        }
        return false;
    }

}
