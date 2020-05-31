package com.course.service;

import com.course.entity.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getLessonsByCourseId(String courseId);
}
