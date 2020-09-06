package com.course.services;

import com.course.entity.CourseEntity;
import com.course.entity.LessonEntity;
import com.course.entity.UserEntity;
import com.course.repository.LessonRepository;
import com.course.service.CourseServiceImpl;
import com.course.service.LessonServiceImpl;
import com.course.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LessonEntityServiceTests {

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private CourseServiceImpl courseService;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private LessonServiceImpl lessonService;

    private LessonEntity lessonEntity;
    private Timestamp createDateTime = new Timestamp(System.currentTimeMillis());

    @Before
    public void setup() {
        UserEntity student = new UserEntity("5211e915-c3e2-4dcb-0776-c7b900f38ab7", "John", "Doe", "john.doe@gmail.com", "STUDENT");
        UserEntity teacher = new UserEntity("9128d270-f013-4a24-9d04-da424673203a", "Derp", "Doe", "derp.doe@gmail.com", "TEACHER");
        CourseEntity courseEntity = new CourseEntity("49a493f3-956e-4b1d-956d-9db6df76967f", "Mathematics 7", "Introduction courseEntity in mathematics", teacher, "0c00f4a7-5aaa-439e-8d12-5c154f91b44b");
        student.setCoursesEnrolled(Arrays.asList(courseEntity));
        lessonEntity = new LessonEntity("3b07ff1a-93d7-4b72-9a37-e19ef6993ede", createDateTime,"Auto LessonEntity", "this is a auto lessonEntity", courseEntity);
        courseEntity.setLessonEntities(Arrays.asList(lessonEntity));

        when(userService.findById("5211e915-c3e2-4dcb-0776-c7b900f38ab7")).thenReturn(student);
        when(lessonRepository.save(lessonEntity)).thenReturn(lessonEntity);
        when(courseService.findById("49a493f3-956e-4b1d-956d-9db6df76967f")).thenReturn(courseEntity);
    }

    @Test
    public void saveLesson_byLesson_returnLesson() {
        assertThat(lessonService.save(lessonEntity)).isEqualTo(lessonEntity);
    }

    @Test
    public void getLessons_byCourseId_returnListOfLessons() {
        assertThat(lessonService.getLessonsByCourseId("49a493f3-956e-4b1d-956d-9db6df76967f")).containsExactly(lessonEntity);
    }
}
