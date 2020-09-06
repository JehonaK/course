package com.course.repository;

import com.course.entity.CourseEntity;
import com.course.entity.CustomActivityEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomActivityRepository extends BaseRepository<CustomActivityEntity, String>{
    List<CustomActivityEntity> findByCourseEntityId(CourseEntity courseEntityId);
}
