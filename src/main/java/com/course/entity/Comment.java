package com.course.entity;

import com.course.annotations.ApiEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "comment")
@ApiEntity
public class Comment extends BaseEntity<String> implements Comparable<Comment> {

    @Column(name = "content")
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private ForumPost postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User authorId;

    public Comment() {}

    public Comment(String id, Timestamp createDateTime, String content, ForumPost postId, User authorId) {
        super(id, createDateTime);
        this.content = content;
        this.postId = postId;
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ForumPost getPostId() {
        return postId;
    }

    public void setPostId(ForumPost postId) {
        this.postId = postId;
    }

    public User getAuthorId() {
        return authorId;
    }

    public void setAuthorId(User authorId) {
        this.authorId = authorId;
    }

    @Override
    public int compareTo(Comment o) {
        return this.getCreateDateTime().compareTo(o.getCreateDateTime());
    }

}
