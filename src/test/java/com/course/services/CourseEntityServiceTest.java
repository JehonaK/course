package com.course.services;

import com.course.entity.CourseEntity;
import com.course.entity.CourseClassEntity;
import com.course.entity.UserEntity;
import com.course.service.CourseClassServiceImpl;
import com.course.service.CourseServiceImpl;
import com.course.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseEntityServiceTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private CourseClassServiceImpl courseClassService;

    @InjectMocks
    private CourseServiceImpl courseService;

    private CourseEntity courseEntity;

    @Before
    public void setup() {
        UserEntity student = new UserEntity("5211e915-c3e2-4dcb-0776-c7b900f38ab7", "John", "Doe", "john.doe@gmail.com", "STUDENT");
        UserEntity teacher = new UserEntity("9128d270-f013-4a24-9d04-da424673203a", "Derp", "Doe", "derp.doe@gmail.com", "TEACHER");
        CourseClassEntity courseClassEntity = new CourseClassEntity("eeafa363-db82-4159-8b61-549ef26e2a1f", "Mathematics");
        courseEntity = new CourseEntity("49a493f3-956e-4b1d-956d-9db6df76967f", "Mathematics 7", "Introduction courseEntity in mathematics", teacher, "0c00f4a7-5aaa-439e-8d12-5c154f91b44b");
        List<CourseEntity> cours = Arrays.asList(courseEntity);
        student.setCoursesEnrolled(cours);
        teacher.setCoursesTeaching(cours);
        courseClassEntity.setCours(cours);
        when(userService.findById("5211e915-c3e2-4dcb-0776-c7b900f38ab7")).thenReturn(student);
        when(userService.findById("9128d270-f013-4a24-9d04-da424673203a")).thenReturn(teacher);
        when(courseClassService.findById("eeafa363-db82-4159-8b61-549ef26e2a1f")).thenReturn(courseClassEntity);
    }

    @Test
    public void getCourses_byTeacherId_returnListOfCourses() {
        assertThat(courseService.getCoursesByTeacherId("9128d270-f013-4a24-9d04-da424673203a")).containsExactly(courseEntity);
    }

    @Test
    public void getCourses_byStudentId_returnListOfCourses() {
        assertThat(courseService.getCoursesByStudentId("5211e915-c3e2-4dcb-0776-c7b900f38ab7")).containsExactly(courseEntity);
    }

    @Test
    public void getCourses_byCourseClassId_returnListOfCourses() {
        assertThat(courseService.getCoursesByCourseClassId("eeafa363-db82-4159-8b61-549ef26e2a1f")).containsExactly(courseEntity);
    }

}
