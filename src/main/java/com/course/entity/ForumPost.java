package com.course.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "forum_post")
public class ForumPost extends BaseEntity<String>{

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courseId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User authorId;

    @OneToMany(mappedBy = "postId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    public ForumPost() {}

    public ForumPost(String id, Timestamp createDateTime, String title, String content, Course courseId, User authorId) {
        super(id, createDateTime);
        this.title = title;
        this.content = content;
        this.courseId = courseId;
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

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public User getAuthorId() {
        return authorId;
    }

    public void setAuthorId(User authorId) {
        this.authorId = authorId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
