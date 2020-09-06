package com.course.service;

import com.course.entity.ActivityEntity;

import java.util.List;

public interface ActivityService {
    List<ActivityEntity> getActivitiesByCourseId(String courseId);
}
