package com.course.repository;

import com.course.entity.ForumPostEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumPostRepository extends BaseRepository<ForumPostEntity, String> {

}
