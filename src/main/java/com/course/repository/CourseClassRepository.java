package com.course.repository;

import com.course.entity.Course;
import com.course.entity.CourseClass;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseClassRepository extends BaseRepository<CourseClass, String>{
}
