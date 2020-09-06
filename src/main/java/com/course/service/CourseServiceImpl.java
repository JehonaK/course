package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.CourseEntity;
import com.course.entity.CourseClassEntity;
import com.course.entity.UserEntity;
import com.course.integration.models.SerializableTeacherSubjectConnection;
import com.course.repository.BaseRepository;
import com.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl extends BaseServiceImpl<CourseEntity, String> implements CourseService {

    private CourseRepository courseRepository;
    private UserServiceImpl userService;
    private CourseClassServiceImpl courseClassService;

    public CourseServiceImpl(BaseRepository<CourseEntity, String> baseRepository, CourseRepository courseRepository, UserServiceImpl userService, CourseClassServiceImpl courseClassService) {
        super(baseRepository);
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.courseClassService = courseClassService;
    }

    @Override
    public List<CourseEntity> getCoursesByTeacherId(String teacherId) {
        teacherId = teacherId == null ? PerRequestIdStorage.getUserId() : teacherId;
        UserEntity teacher = userService.findById(teacherId);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found!");
        }
        return teacher.getCoursesTeaching();
    }

    @Override
    public List<CourseEntity> getCoursesByStudentId(String studentId) {
        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
        UserEntity student = userService.findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found!");
        }
        return student.getCoursesEnrolled();
    }

    public List<CourseEntity> getCoursesByCourseClassId(String courseClassId){
        CourseClassEntity courseClassEntity = courseClassService.findById(courseClassId);
        return courseClassEntity.getCours();
    }

    @Override
    public void handleNewTeacherSubjectConnection(SerializableTeacherSubjectConnection connection) {
        UserEntity teacher = userService.findById(connection.getTeacherId());
        if (teacher == null) {
            throw new RuntimeException("Teacher not found!");
        }
        List<UserEntity> students = new ArrayList<>();
        for (String studentId : connection.getStudentsId()) {
            UserEntity student = userService.findById(studentId);
            if (student == null) {
                throw new RuntimeException("Student not found!");
            }
            students.add(student);
        }
        CourseEntity courseEntity = new CourseEntity(connection.getCourseName(), "No description", teacher, connection.getSubjectId());
        courseEntity.setStudents(students);
        save(courseEntity);
    }

}
