package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.Course;
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

    public CourseServiceImpl(BaseRepository<Course, String> baseRepository, CourseRepository courseRepository, UserServiceImpl userService) {
        super(baseRepository);
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    @Override
    public List<Course> getCoursesByTeacherId(String teacherId) {
        teacherId = teacherId == null ? PerRequestIdStorage.getUserId() : teacherId;
        User teacher = userService.findById(teacherId);
        return teacher.getCoursesTeaching();
    }

    @Override
    public List<Course> getCoursesByStudentId(String studentId) {
//        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
//        User student = userService.findById(studentId);
//        return student.getCoursesEnrolled();
        ArrayList<String> sl = new ArrayList<>();
        sl.add("58fd62c1-0b58-456f-8c3b-21bdf5739d3");
        sl.add("222");
        SerializableTeacherSubjectConnection con = new SerializableTeacherSubjectConnection("MAth 1", "6619afe0-f0ef-4aa5-a1e4-a597e8174bbf", "1", sl);
        handleNewTeacherSubjectConnection(con);
        return null;
    }

    @Override
    public void handleNewTeacherSubjectConnection(SerializableTeacherSubjectConnection connection) {
        User teacher = userService.findById(connection.getTeacherId());
        List<User> students = userService.findBatchOfUsersByIdList(connection.getStudentsId());
        Course course = new Course(connection.getCourseName(), "No description", teacher, connection.getSubjectId());
        course.setStudents(students);
        save(course);
    }
}
