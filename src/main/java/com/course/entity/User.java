package com.course.entity;

import com.course.annotations.ApiEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@ApiEntity
public class User extends BaseEntity<String> {

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
    private List<ForumPost> forumPosts;

    @JsonIgnore
    @OneToMany(mappedBy = "teacherId", fetch = FetchType.LAZY)
    private List<Course> coursesTeaching;

    @JsonIgnore
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Course> coursesEnrolled;

    @JsonIgnore
    @OneToMany(mappedBy = "studentId", fetch = FetchType.LAZY)
    private List<Evaluation> evaluations;

    @JsonIgnore
    @OneToMany(mappedBy = "uploadedBy", fetch = FetchType.LAZY)
    private List<FileUpload> fileUploads;

    public User() {}

    public User(String id, String firstName, String lastName, String email, String role) {
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

    public List<ForumPost> getForumPosts() {
        return forumPosts;
    }

    public void setForumPosts(List<ForumPost> forumPosts) {
        this.forumPosts = forumPosts;
    }

    public List<Course> getCoursesTeaching() {
        return coursesTeaching;
    }

    public void setCoursesTeaching(List<Course> coursesTeaching) {
        this.coursesTeaching = coursesTeaching;
    }

    public List<Course> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(List<Course> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public List<FileUpload> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(List<FileUpload> fileUploads) {
        this.fileUploads = fileUploads;
    }

}
