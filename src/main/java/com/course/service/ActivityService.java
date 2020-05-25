package com.course.service;

import com.course.entity.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> getActivitiesByCourseId(String courseId);
}
