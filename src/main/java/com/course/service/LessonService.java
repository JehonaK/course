package com.course.service;

import com.course.entity.LessonEntity;

import java.util.List;

public interface LessonService {
    List<LessonEntity> getLessonsByCourseId(String courseId);
}
