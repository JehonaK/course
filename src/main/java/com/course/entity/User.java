package com.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity<String> {

    private String firstName;
    private String lastName;
    private String email;
    private String role;

    @OneToMany(mappedBy = "authorId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ForumPost> forumPosts;

    @OneToMany(mappedBy = "teacherId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courses;

    @ManyToMany(mappedBy = "students")
    private List<Course> coursesEnrolled;

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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(List<Course> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }
}
