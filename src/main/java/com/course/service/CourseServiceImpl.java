package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.Course;
import com.course.entity.CourseClass;
import com.course.entity.User;
import com.course.integration.models.SerializableTeacherSubjectConnection;
import com.course.repository.BaseRepository;
import com.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, String> implements CourseService {

    private CourseRepository courseRepository;
    private UserServiceImpl userService;
    private CourseClassServiceImpl courseClassService;

    public CourseServiceImpl(BaseRepository<Course, String> baseRepository, CourseRepository courseRepository, UserServiceImpl userService, CourseClassServiceImpl courseClassService) {
        super(baseRepository);
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.courseClassService = courseClassService;
    }

    @Override
    public List<Course> getCoursesByTeacherId(String teacherId) {
        teacherId = teacherId == null ? PerRequestIdStorage.getUserId() : teacherId;
        User teacher = userService.findById(teacherId);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found!");
        }
        return teacher.getCoursesTeaching();
    }

    @Override
    public List<Course> getCoursesByStudentId(String studentId) {
        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
        User student = userService.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found!");
        }
        return student.getCoursesEnrolled();
    }

    public List<Course> getCoursesByCourseClassId(String courseClassId){
        CourseClass courseClass = courseClassService.findById(courseClassId);
        return courseClass.getCourses();
    }

    @Override
    public void handleNewTeacherSubjectConnection(SerializableTeacherSubjectConnection connection) {
        User teacher = userService.findById(connection.getTeacherId());
        if (teacher == null) {
            throw new RuntimeException("Teacher not found!");
        }
        List<User> students = new ArrayList<>();
        for (String studentId : connection.getStudentsId()) {
            User student = userService.findById(studentId);
            if (student == null) {
                throw new RuntimeException("Student not found!");
            }
            students.add(student);
        }
        Course course = new Course(connection.getCourseName(), "No description", teacher, connection.getSubjectId());
        course.setStudents(students);
        save(course);
    }

}
