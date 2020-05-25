package com.course.repository;

import com.course.entity.Activity;
import com.course.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends BaseRepository<Activity, String>{
    List<Activity> findByCourseId(Course courseId);
}
