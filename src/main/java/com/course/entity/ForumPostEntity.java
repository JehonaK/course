package com.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "forum_post")
public class ForumPostEntity extends BaseEntity<String>{

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntityId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity authorId;

    @OneToMany(mappedBy = "forumPostEntityId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntities;

    public ForumPostEntity() {}

    public ForumPostEntity(String id, Timestamp createDateTime, String title, String content, CourseEntity courseEntityId, UserEntity authorId) {
        super(id, createDateTime);
        this.title = title;
        this.content = content;
        this.courseEntityId = courseEntityId;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CourseEntity getCourseEntityId() {
        return courseEntityId;
    }

    public void setCourseEntityId(CourseEntity courseEntityId) {
        this.courseEntityId = courseEntityId;
    }

    public UserEntity getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UserEntity authorId) {
        this.authorId = authorId;
    }

    public List<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof ForumPostEntity) {
            return this.getId().equals(((ForumPostEntity)object).getId());
        }
        return false;
    }

}
