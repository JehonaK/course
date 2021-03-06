package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.Course;
import com.course.entity.ForumPost;
import com.course.entity.User;
import com.course.integration.models.SerializableNotification;
import com.course.integration.producers.NotificationProducer;
import com.course.repository.BaseRepository;
import com.course.repository.ForumPostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ForumPostServiceImpl extends BaseServiceImpl<ForumPost, String> implements ForumPostService {

    private ForumPostRepository forumPostRepository;
    private CourseServiceImpl courseService;
    private UserServiceImpl userService;
    private NotificationProducer notificationProducer;

    public ForumPostServiceImpl(BaseRepository<ForumPost, String> baseRepository, ForumPostRepository forumPostRepository, CourseServiceImpl courseService,
                                UserServiceImpl userService, NotificationProducer notificationProducer) {
        super(baseRepository);
        this.forumPostRepository = forumPostRepository;
        this.courseService = courseService;
        this.userService = userService;
        this.notificationProducer = notificationProducer;
    }

    @Override
    public ForumPost save(ForumPost forumPost) {
        if(forumPost.getAuthorId() == null) {
            User author = userService.findById(PerRequestIdStorage.getUserId());
            if (author == null) {
                throw new RuntimeException("User not found!");
            }
            forumPost.setAuthorId(author);
        }
        ForumPost savedForumPost = forumPostRepository.save(forumPost);
        ArrayList<String> recipients = new ArrayList<>();
        for (User recipient : savedForumPost.getCourseId().getStudents()) {
            recipients.add(recipient.getId());
        }
        String content = "New post by " + forumPost.getAuthorId().getFirstName() + " in " + forumPost.getCourseId().getName();
        SerializableNotification serializableNotification = new SerializableNotification(content, recipients);
        notificationProducer.sendNotification(serializableNotification);
        return savedForumPost;
    }

    @Override
    public List<ForumPost> getForumPostsByStudentId(String studentId) {
        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
        User student = userService.findById(studentId);
        List<Course> courses = student.getCoursesEnrolled();
        List<ForumPost> forumPosts = new ArrayList<>();
        for(Course course : courses) {
            forumPosts.addAll(course.getForumPosts());
        }
        return forumPosts;
    }

    @Override
    public List<ForumPost> getForumPostByCourseId(String courseId) {
        Course course = courseService.findById(courseId);
        List<ForumPost> forumPosts = course.getForumPosts();
        forumPosts.forEach(forumPost -> {
            if(forumPost.getComments() != null) Collections.sort(forumPost.getComments());
        });
        return forumPosts;
    }

}
