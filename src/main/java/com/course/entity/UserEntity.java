package com.course.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity<String> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "authorId", fetch = FetchType.LAZY)
    private List<ForumPostEntity> forumPostEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "teacherId", fetch = FetchType.LAZY)
    private List<CourseEntity> coursesTeaching;

    @JsonIgnore
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<CourseEntity> coursesEnrolled;

    @JsonIgnore
    @OneToMany(mappedBy = "studentId", fetch = FetchType.LAZY)
    private List<EvaluationEntity> evaluationEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "uploadedBy", fetch = FetchType.LAZY)
    private List<FileUploadEntity> fileUploadEntities;

    public UserEntity() {}

    public UserEntity(String id, String firstName, String lastName, String email, String role) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ForumPostEntity> getForumPostEntities() {
        return forumPostEntities;
    }

    public void setForumPostEntities(List<ForumPostEntity> forumPostEntities) {
        this.forumPostEntities = forumPostEntities;
    }

    public List<CourseEntity> getCoursesTeaching() {
        return coursesTeaching;
    }

    public void setCoursesTeaching(List<CourseEntity> coursesTeaching) {
        this.coursesTeaching = coursesTeaching;
    }

    public List<CourseEntity> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(List<CourseEntity> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    public List<EvaluationEntity> getEvaluationEntities() {
        return evaluationEntities;
    }

    public void setEvaluationEntities(List<EvaluationEntity> evaluationEntities) {
        this.evaluationEntities = evaluationEntities;
    }

    public List<FileUploadEntity> getFileUploadEntities() {
        return fileUploadEntities;
    }

    public void setFileUploadEntities(List<FileUploadEntity> fileUploadEntities) {
        this.fileUploadEntities = fileUploadEntities;
    }

}
