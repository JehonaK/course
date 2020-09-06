package com.course.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course_class")
public class CourseClassEntity extends BaseEntity<String> {
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "courseClassEntities", fetch = FetchType.LAZY)
    private List<CourseEntity> cours;

    public CourseClassEntity() {
    }

    public CourseClassEntity(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseEntity> getCours() {
        return cours;
    }

    public void setCours(List<CourseEntity> cours) {
        this.cours = cours;
    }
}
