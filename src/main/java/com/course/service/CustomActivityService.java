package com.course.service;

import com.course.entity.CustomActivity;

import java.util.List;

public interface CustomActivityService {
    List<CustomActivity> getCustomActivityByCourseId(String courseId);
}
