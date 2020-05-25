package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.Course;
import com.course.entity.User;
import com.course.repository.BaseRepository;
import com.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, String> implements CourseService {

    private CourseRepository courseRepository;
    private UserServiceImpl userService;

    public CourseServiceImpl(BaseRepository<Course, String> baseRepository, CourseRepository courseRepository, UserServiceImpl userService) {
        super(baseRepository);
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    @Override
    public List<Course> getCoursesByTeacherId(String teacherId) {
        teacherId = teacherId == null ? PerRequestIdStorage.getUserId() : teacherId;
        User teacher = userService.findById(teacherId);
        return teacher.getCourses();
    }

    @Override
    public List<Course> getCoursesByStudentId(String studentId) {
        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
        User student = userService.findById(studentId);
        return student.getCoursesEnrolled();
    }

}
