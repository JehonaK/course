package com.course.repository;

import com.course.entity.ForumPost;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumPostRepository extends BaseRepository<ForumPost, String> {

}
