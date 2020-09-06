package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.CommentEntity;
import com.course.entity.ForumPostEntity;
import com.course.entity.UserEntity;
import com.course.integration.producers.NotificationProducer;
import com.course.repository.BaseRepository;
import com.course.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseServiceImpl<CommentEntity, String> implements CommentService {

    private CommentRepository commentRepository;
    private UserServiceImpl userService;
    private NotificationProducer notificationProducer;
    private ForumPostServiceImpl forumPostService;

    public CommentServiceImpl(BaseRepository<CommentEntity, String> baseRepository, CommentRepository commentRepository, UserServiceImpl userService,
                              NotificationProducer notificationProducer, ForumPostServiceImpl forumPostService) {
        super(baseRepository);
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.notificationProducer = notificationProducer;
        this.forumPostService = forumPostService;
    }

    @Override
    public CommentEntity saveByForumPostId(CommentEntity comment, String forumPostId) {
        UserEntity author = userService.findById(PerRequestIdStorage.getUserId());
        if (author == null) {
            throw new RuntimeException("Author not found!");
        }
        ForumPostEntity forumPostEntity = forumPostService.findById(forumPostId);
        if (forumPostEntity == null) {
            throw new RuntimeException("Forum Post not found!");
        }
        comment.setAuthorId(author);
        comment.setForumPostEntityId(forumPostEntity);
//        notificationProducer.sendNotification(new SerializableNotification("CommentEntity \"" + comment.getContent() + "\" in forum post with title \""
//                + comment.getForumPostEntityId().getTitle() + "\"", comment.getForumPostEntityId().getCourseEntityId().getStudents().stream().map(UserEntity::getId).collect(Collectors.toCollection(ArrayList::new))));
        return commentRepository.save(comment);
    }

}
