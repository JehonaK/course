package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.Course;
import com.course.entity.ForumPost;
import com.course.repository.BaseRepository;
import com.course.repository.ForumPostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumPostServiceImpl extends BaseServiceImpl<ForumPost, String> implements ForumPostService {

    private ForumPostRepository forumPostRepository;
    private CourseServiceImpl courseService;

    public ForumPostServiceImpl(BaseRepository<ForumPost, String> baseRepository, ForumPostRepository forumPostRepository, CourseServiceImpl courseService) {
        super(baseRepository);
        this.forumPostRepository = forumPostRepository;
        this.courseService = courseService;
    }

    @Override
    public List<ForumPost> getForumPostsByStudentId(String studentId) {
        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
        List<Course> courses = courseService.getCoursesByStudentId(studentId);
        List<ForumPost> forumPosts = new ArrayList<>();
        for(Course course : courses) {
            forumPosts.addAll(course.getForumPosts());
        }
        return forumPosts;
    }

    @Override
    public List<ForumPost> getForumPostByCourseId(String courseId) {
        Course course = courseService.findById(courseId);
        return course.getForumPosts();
    }
}
