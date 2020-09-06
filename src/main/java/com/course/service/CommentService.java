package com.course.service;

import com.course.entity.CommentEntity;

public interface CommentService {

    CommentEntity saveByForumPostId(CommentEntity commentEntity, String forumPostId);

}
