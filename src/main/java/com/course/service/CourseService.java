package com.course.service;

import com.course.entity.CourseEntity;
import com.course.integration.models.SerializableTeacherSubjectConnection;

import java.util.List;

public interface CourseService {
    List<CourseEntity> getCoursesByTeacherId(String teacherId);

    List<CourseEntity> getCoursesByStudentId(String studentId);

    List<CourseEntity> getCoursesByCourseClassId(String courseClassId);

    void handleNewTeacherSubjectConnection(SerializableTeacherSubjectConnection connection);
}
