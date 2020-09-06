package com.course.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class CourseEntity extends BaseEntity<String>{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "subject_id")
    private String subjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private UserEntity teacherId;

    @JsonIgnore
    @OneToMany(mappedBy = "courseEntityId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ActivityEntity> activities;

    @JsonIgnore
    @OneToMany(mappedBy = "courseEntityId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomActivityEntity> customActivities;

    @JsonIgnore
    @OneToMany(mappedBy = "courseEntityId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ForumPostEntity> forumPostEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "courseEntityId", fetch = FetchType.LAZY)
    private List<LessonEntity> lessonEntities;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_student",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private List<UserEntity> students;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_class",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "class_id") }
    )
    private List<CourseClassEntity> courseClassEntities;

    public CourseEntity() {}

    public CourseEntity(String name, String description, UserEntity teacherId, String subjectId) {
        this.name = name;
        this.description = description;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public CourseEntity(String id, String name, String description, UserEntity teacherId, String subjectId) {
        super(id);
        this.name = name;
        this.description = description;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public CourseEntity(String id, String name, String description, UserEntity teacherId) {
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

    public UserEntity getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UserEntity teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public List<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }

    public List<CustomActivityEntity> getCustomActivities() {
        return customActivities;
    }

    public void setCustomActivities(List<CustomActivityEntity> customActivities) {
        this.customActivities = customActivities;
    }

    public List<ForumPostEntity> getForumPostEntities() {
        return forumPostEntities;
    }

    public void setForumPostEntities(List<ForumPostEntity> forumPostEntities) {
        this.forumPostEntities = forumPostEntities;
    }

    public List<UserEntity> getStudents() {
        return students;
    }

    public void setStudents(List<UserEntity> students) {
        this.students = students;
    }

    public List<LessonEntity> getLessonEntities() {
        return lessonEntities;
    }

    public void setLessonEntities(List<LessonEntity> lessonEntities) {
        this.lessonEntities = lessonEntities;
    }

    public List<CourseClassEntity> getCourseClassEntities() {
        return courseClassEntities;
    }

    public void setCourseClassEntities(List<CourseClassEntity> students) {
        this.courseClassEntities = courseClassEntities;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof CourseEntity) {
            return this.getId().equals(((CourseEntity)object).getId());
        }
        return false;
    }

}
