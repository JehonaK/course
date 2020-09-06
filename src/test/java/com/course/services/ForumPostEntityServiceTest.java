package com.course.services;

import com.course.entity.CourseEntity;
import com.course.entity.ForumPostEntity;
import com.course.entity.UserEntity;
import com.course.repository.ForumPostRepository;
import com.course.service.CourseServiceImpl;
import com.course.service.ForumPostServiceImpl;
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
public class ForumPostEntityServiceTest {

    @Mock
    private ForumPostRepository forumPostRepository;

    @Mock
    private CourseServiceImpl courseService;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private ForumPostServiceImpl forumPostService;

    private ForumPostEntity forumPostEntity;
    private Timestamp createDateTime = new Timestamp(System.currentTimeMillis());

    @Before
    public void setup() {
        UserEntity student = new UserEntity("5211e915-c3e2-4dcb-0776-c7b900f38ab7", "John", "Doe", "john.doe@gmail.com", "STUDENT");
        UserEntity teacher = new UserEntity("9128d270-f013-4a24-9d04-da424673203a", "Derp", "Doe", "derp.doe@gmail.com", "TEACHER");
        CourseEntity courseEntity = new CourseEntity("49a493f3-956e-4b1d-956d-9db6df76967f", "Mathematics 7", "Introduction courseEntity in mathematics", teacher, "0c00f4a7-5aaa-439e-8d12-5c154f91b44b");
        student.setCoursesEnrolled(Arrays.asList(courseEntity));
        forumPostEntity = new ForumPostEntity("3b07ff1a-93d7-4b72-9a37-e19ef6993ede", createDateTime,"forum post", "post content", courseEntity, student);
        courseEntity.setForumPostEntities(Arrays.asList(forumPostEntity));
        when(userService.findById("5211e915-c3e2-4dcb-0776-c7b900f38ab7")).thenReturn(student);
        when(forumPostRepository.save(forumPostEntity)).thenReturn(forumPostEntity);
        when(courseService.findById("49a493f3-956e-4b1d-956d-9db6df76967f")).thenReturn(courseEntity);
    }

    @Test
    public void saveForumPost_byForumPost_returnForumPost() {
        assertThat(forumPostService.save(forumPostEntity)).isEqualTo(forumPostEntity);
    }

    @Test
    public void getForumPosts_byStudentId_returnListOfForumPosts() {
        assertThat(forumPostService.getForumPostsByStudentId("5211e915-c3e2-4dcb-0776-c7b900f38ab7")).containsExactly(forumPostEntity);
    }

    @Test
    public void getForumPosts_byCourseId_returnListOfForumPosts() {
        assertThat(forumPostService.getForumPostByCourseId("49a493f3-956e-4b1d-956d-9db6df76967f")).containsExactly(forumPostEntity);
    }

}
