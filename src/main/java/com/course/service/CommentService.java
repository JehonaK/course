package com.course.service;

import com.course.entity.Comment;

public interface CommentService {

    Comment saveByForumPostId(Comment comment, String forumPostId);

}
