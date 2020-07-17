package com.course.services;

import com.course.entity.Course;
import com.course.entity.Lesson;
import com.course.entity.User;
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
public class LessonServiceTests {

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private CourseServiceImpl courseService;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private LessonServiceImpl lessonService;

    private Lesson lesson;
    private Timestamp createDateTime = new Timestamp(System.currentTimeMillis());

    @Before
    public void setup() {
        User student = new User("5211e915-c3e2-4dcb-0776-c7b900f38ab7", "John", "Doe", "john.doe@gmail.com", "STUDENT");
        User teacher = new User("9128d270-f013-4a24-9d04-da424673203a", "Derp", "Doe", "derp.doe@gmail.com", "TEACHER");
        Course course = new Course("49a493f3-956e-4b1d-956d-9db6df76967f", "Mathematics 7", "Introduction course in mathematics", teacher, "0c00f4a7-5aaa-439e-8d12-5c154f91b44b");
        student.setCoursesEnrolled(Arrays.asList(course));
        lesson = new Lesson("3b07ff1a-93d7-4b72-9a37-e19ef6993ede", createDateTime,"Auto Lesson", "this is a auto lesson", course);
        course.setLessons(Arrays.asList(lesson));

        when(userService.findById("5211e915-c3e2-4dcb-0776-c7b900f38ab7")).thenReturn(student);
        when(lessonRepository.save(lesson)).thenReturn(lesson);
        when(courseService.findById("49a493f3-956e-4b1d-956d-9db6df76967f")).thenReturn(course);
    }

    @Test
    public void saveLesson_byLesson_returnLesson() {
        assertThat(lessonService.save(lesson)).isEqualTo(lesson);
    }

    @Test
    public void getLessons_byCourseId_returnListOfLessons() {
        assertThat(lessonService.getLessonsByCourseId("49a493f3-956e-4b1d-956d-9db6df76967f")).containsExactly(lesson);
    }
}
