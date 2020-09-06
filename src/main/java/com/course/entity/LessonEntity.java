package com.course.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class LessonEntity extends BaseEntity<String> implements Comparable<LessonEntity> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntityId;

    @JsonIgnore
    @OneToMany(mappedBy = "lessonEntityId", fetch = FetchType.LAZY)
    private List<FileUploadEntity> fileUploadEntities;

    public LessonEntity() { }

    public LessonEntity(String name, String description, CourseEntity courseEntityId, List<FileUploadEntity> fileUploadEntities) {
        this.name = name;
        this.description = description;
        this.courseEntityId = courseEntityId;
        this.fileUploadEntities = fileUploadEntities;
    }

    public LessonEntity(String id, Timestamp createDateTime, String name, String description, CourseEntity courseEntityId){
        super(id, createDateTime);
        this.name = name;
        this.description = description;
        this.courseEntityId = courseEntityId;
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

    public CourseEntity getCourseEntityId() {
        return courseEntityId;
    }

    public void setCourseEntityId(CourseEntity courseEntityId) {
        this.courseEntityId = courseEntityId;
    }

    public List<FileUploadEntity> getFileUploadEntities() {
        return fileUploadEntities;
    }

    public void setFileUploadEntities(List<FileUploadEntity> fileUploadEntities) {
        this.fileUploadEntities = fileUploadEntities;
    }

    @Override
    public int compareTo(LessonEntity o) {
        return getCreateDateTime().compareTo(o.getCreateDateTime());
    }

}
