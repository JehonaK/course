package com.course.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity<String> implements Comparable<CommentEntity> {

    @Column(name = "content")
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private ForumPostEntity forumPostEntityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserEntity authorId;

    public CommentEntity() {}

    public CommentEntity(String id, Timestamp createDateTime, String content, ForumPostEntity postId, UserEntity authorId) {
        super(id, createDateTime);
        this.content = content;
        this.forumPostEntityId = postId;
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ForumPostEntity getForumPostEntityId() {
        return forumPostEntityId;
    }

    public void setForumPostEntityId(ForumPostEntity forumPostEntityId) {
        this.forumPostEntityId = forumPostEntityId;
    }

    public UserEntity getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UserEntity authorId) {
        this.authorId = authorId;
    }

    @Override
    public int compareTo(CommentEntity o) {
        return this.getCreateDateTime().compareTo(o.getCreateDateTime());
    }

}
