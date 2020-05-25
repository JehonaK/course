package com.course.service;

import com.course.entity.Comment;
import com.course.repository.BaseRepository;
import com.course.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment, String> implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(BaseRepository<Comment, String> baseRepository, CommentRepository commentRepository) {
        super(baseRepository);
        this.commentRepository = commentRepository;
    }

}
