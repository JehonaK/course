package com.course.services;

import com.course.entity.Activity;
import com.course.entity.Course;
import com.course.entity.User;
import com.course.repository.ActivityRepository;
import com.course.service.ActivityServiceImpl;
import com.course.service.CourseServiceImpl;
import com.course.service.UserServiceImpl;
import com.course.type.GradeSystem;
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
public class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private CourseServiceImpl courseService;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private ActivityServiceImpl activityService;

    private Activity activity;
    private Timestamp deadline = new Timestamp(System.currentTimeMillis());

    @Before
    public void setup() {
        User student = new User("5211e915-c3e2-4dcb-0776-c7b900f38ab7", "John", "Doe", "john.doe@gmail.com", "STUDENT");
        User teacher = new User("9128d270-f013-4a24-9d04-da424673203a", "Derp", "Doe", "derp.doe@gmail.com", "TEACHER");
        Course course = new Course("49a493f3-956e-4b1d-956d-9db6df76967f", "Mathematics 7", "Introduction course in mathematics", teacher, "0c00f4a7-5aaa-439e-8d12-5c154f91b44b");
        student.setCoursesEnrolled(Arrays.asList(course));
        activity = new Activity("3b07ff1a-93d7-4b72-9a37-e19ef6993ede",
                "Auto activity",
                deadline,
                "This is a test activity",
                false,
                GradeSystem.A_F,
                "A",
                course
                );
        course.setActivities(Arrays.asList(activity));

        when(userService.findById("5211e915-c3e2-4dcb-0776-c7b900f38ab7")).thenReturn(student);
        when(activityRepository.save(activity)).thenReturn(activity);
        when(courseService.findById("49a493f3-956e-4b1d-956d-9db6df76967f")).thenReturn(course);
    }

    @Test
    public void saveActivity_byActivity_returnActivity() {
        assertThat(activityService.save(activity)).isEqualTo(activity);
    }

    @Test
    public void getActivities_byCourseId_returnListOfActivities() {
        assertThat(activityService.getActivitiesByCourseId("49a493f3-956e-4b1d-956d-9db6df76967f")).containsExactly(activity);
    }
}
