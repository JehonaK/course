package com.course.repository;

import com.course.entity.Course;
import com.course.entity.CustomActivity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomActivityRepository extends BaseRepository<CustomActivity, String>{
    List<CustomActivity> findByCourseId(Course courseId);
}
