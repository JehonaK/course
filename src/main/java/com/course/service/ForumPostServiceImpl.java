package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.CourseEntity;
import com.course.entity.ForumPostEntity;
import com.course.entity.UserEntity;
import com.course.integration.producers.NotificationProducer;
import com.course.repository.BaseRepository;
import com.course.repository.ForumPostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ForumPostServiceImpl extends BaseServiceImpl<ForumPostEntity, String> implements ForumPostService {

    private ForumPostRepository forumPostRepository;
    private CourseServiceImpl courseService;
    private UserServiceImpl userService;
    private NotificationProducer notificationProducer;

    public ForumPostServiceImpl(BaseRepository<ForumPostEntity, String> baseRepository, ForumPostRepository forumPostRepository, CourseServiceImpl courseService,
                                UserServiceImpl userService, NotificationProducer notificationProducer) {
        super(baseRepository);
        this.forumPostRepository = forumPostRepository;
        this.courseService = courseService;
        this.userService = userService;
        this.notificationProducer = notificationProducer;
    }

    @Override
    public ForumPostEntity save(ForumPostEntity forumPostEntity) {
        if(forumPostEntity.getAuthorId() == null) {
            UserEntity author = userService.findById(PerRequestIdStorage.getUserId());
            if (author == null) {
                throw new RuntimeException("UserEntity not found!");
            }
            forumPostEntity.setAuthorId(author);
        }
        return forumPostRepository.save(forumPostEntity);
    }

    @Override
    public List<ForumPostEntity> getForumPostsByStudentId(String studentId) {
        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
        UserEntity student = userService.findById(studentId);
        List<CourseEntity> cours = student.getCoursesEnrolled();
        List<ForumPostEntity> forumPostEntities = new ArrayList<>();
        for(CourseEntity courseEntity : cours) {
            forumPostEntities.addAll(courseEntity.getForumPostEntities());
        }
        return forumPostEntities;
    }

    @Override
    public List<ForumPostEntity> getForumPostByCourseId(String courseId) {
        CourseEntity courseEntity = courseService.findById(courseId);
        List<ForumPostEntity> forumPostEntities = courseEntity.getForumPostEntities();
        forumPostEntities.forEach(forumPost -> {
            if(forumPost.getCommentEntities() != null) Collections.sort(forumPost.getCommentEntities());
        });
        return forumPostEntities;
    }

}
