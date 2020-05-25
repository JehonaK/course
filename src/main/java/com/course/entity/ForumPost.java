package com.course.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "forum_post")
public class ForumPost extends BaseEntity<String>{

    private String tile;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course courseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User authorId;

    @OneToMany(mappedBy = "postId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    public ForumPost(String id, Timestamp createDateTime, String tile, String content, Course courseId, User authorId) {
        super(id, createDateTime);
        this.tile = tile;
        this.content = content;
        this.courseId = courseId;
        this.authorId = authorId;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
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
