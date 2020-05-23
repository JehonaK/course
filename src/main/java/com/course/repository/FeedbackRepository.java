package com.course.repository;

import com.course.entity.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends BaseRepository<Feedback, String>{
}
