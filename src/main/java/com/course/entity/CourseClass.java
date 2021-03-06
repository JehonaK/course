package com.course.entity;

import com.course.annotations.ApiEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course_class")
@ApiEntity
public class CourseClass extends BaseEntity<String> {
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "courseClasses", fetch = FetchType.LAZY)
    private List<Course> courses;

    public CourseClass() {
    }

    public CourseClass(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
