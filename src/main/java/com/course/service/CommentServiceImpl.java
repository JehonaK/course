package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.Comment;
import com.course.entity.ForumPost;
import com.course.entity.User;
import com.course.integration.models.SerializableNotification;
import com.course.integration.producers.NotificationProducer;
import com.course.repository.BaseRepository;
import com.course.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment, String> implements CommentService {

    private CommentRepository commentRepository;
    private UserServiceImpl userService;
    private NotificationProducer notificationProducer;
    private ForumPostServiceImpl forumPostService;

    public CommentServiceImpl(BaseRepository<Comment, String> baseRepository, CommentRepository commentRepository, UserServiceImpl userService,
                              NotificationProducer notificationProducer, ForumPostServiceImpl forumPostService) {
        super(baseRepository);
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.notificationProducer = notificationProducer;
        this.forumPostService = forumPostService;
    }

    @Override
    public Comment saveByForumPostId(Comment comment, String forumPostId) {
        User author = userService.findById(PerRequestIdStorage.getUserId());
        if (author == null) {
            throw new RuntimeException("Author not found!");
        }
        ForumPost forumPost = forumPostService.findById(forumPostId);
        if (forumPost == null) {
            throw new RuntimeException("Forum Post not found!");
        }
        comment.setAuthorId(author);
        comment.setPostId(forumPost);
//        notificationProducer.sendNotification(new SerializableNotification("Comment \"" + comment.getContent() + "\" in forum post with title \""
//                + comment.getPostId().getTitle() + "\"", comment.getPostId().getCourseId().getStudents().stream().map(User::getId).collect(Collectors.toCollection(ArrayList::new))));
        return commentRepository.save(comment);
    }

}
