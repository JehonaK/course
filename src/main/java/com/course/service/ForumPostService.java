package com.course.service;

import com.course.entity.ForumPostEntity;

import java.util.List;

public interface ForumPostService {
    List<ForumPostEntity> getForumPostByCourseId(String courseId);

    List<ForumPostEntity> getForumPostsByStudentId(String studentId);
}
