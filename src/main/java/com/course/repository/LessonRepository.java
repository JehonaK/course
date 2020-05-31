package com.course.repository;

import com.course.entity.Lesson;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends BaseRepository<Lesson, String> {

}
