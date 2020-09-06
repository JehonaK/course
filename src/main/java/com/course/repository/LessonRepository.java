package com.course.repository;

import com.course.entity.LessonEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends BaseRepository<LessonEntity, String> {

}
