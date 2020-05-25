package com.course.service;

import com.course.entity.ForumPost;

import java.util.List;

public interface ForumPostService {
    List<ForumPost> getForumPostByCourseId(String courseId);

    List<ForumPost> getForumPostsByStudentId(String studentId);
}
