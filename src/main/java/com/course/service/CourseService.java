package com.course.service;

import com.course.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCoursesByTeacherId(String teacherId);

    List<Course> getCoursesByStudentId(String studentId);
}
