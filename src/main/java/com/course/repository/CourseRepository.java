package com.course.repository;

import com.course.entity.Course;
import com.course.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends BaseRepository<Course, String>{

}
