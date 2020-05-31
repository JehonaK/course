package com.course.service;

import com.course.entity.Course;
import com.course.integration.models.SerializableTeacherSubjectConnection;

import java.util.List;

public interface CourseService {
    List<Course> getCoursesByTeacherId(String teacherId);

    List<Course> getCoursesByStudentId(String studentId);

    List<Course> getCoursesByCourseClassId(String courseClassId);

    void handleNewTeacherSubjectConnection(SerializableTeacherSubjectConnection connection);
}
