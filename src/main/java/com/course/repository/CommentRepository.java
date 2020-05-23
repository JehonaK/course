package com.course.repository;

import com.course.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends BaseRepository<Comment, String>{
}
