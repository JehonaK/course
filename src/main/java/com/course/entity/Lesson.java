package com.course.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Lesson extends BaseEntity<String> implements Comparable<Lesson> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course courseId;

    @JsonIgnore
    @OneToMany(mappedBy = "lessonId", fetch = FetchType.LAZY)
    private List<FileUpload> fileUploads;

    public Lesson () { }

    public Lesson(String name, String description, Course courseId, List<FileUpload> fileUploads) {
        this.name = name;
        this.description = description;
        this.courseId = courseId;
        this.fileUploads = fileUploads;
    }

    public Lesson(String id, Timestamp createDateTime, String name, String description, Course courseId){
        super(id, createDateTime);
        this.name = name;
        this.description = description;
        this.courseId = courseId;
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

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public List<FileUpload> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(List<FileUpload> fileUploads) {
        this.fileUploads = fileUploads;
    }

    @Override
    public int compareTo(Lesson o) {
        return getCreateDateTime().compareTo(o.getCreateDateTime());
    }

}
