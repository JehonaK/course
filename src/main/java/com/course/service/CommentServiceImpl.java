package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.Comment;
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

    public CommentServiceImpl(BaseRepository<Comment, String> baseRepository, CommentRepository commentRepository, UserServiceImpl userService,
                              NotificationProducer notificationProducer) {
        super(baseRepository);
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.notificationProducer = notificationProducer;
    }

    @Override
    public Comment save(Comment comment) {
        User author = userService.findById(PerRequestIdStorage.getUserId());
        comment.setAuthorId(author);
        notificationProducer.sendNotification(new SerializableNotification("Comment \"" + comment.getContent() + "\" in forum post with title \""
                + comment.getPostId().getTile() + "\"", comment.getPostId().getCourseId().getStudents().stream().map(User::getId).collect(Collectors.toCollection(ArrayList::new))));
        return commentRepository.save(comment);
    }

}
