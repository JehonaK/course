package com.course.repository;

import com.course.entity.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseRepository<Course, String>{
}
