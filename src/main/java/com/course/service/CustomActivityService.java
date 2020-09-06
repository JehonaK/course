package com.course.service;

import com.course.entity.CustomActivityEntity;

import java.util.List;

public interface CustomActivityService {
    List<CustomActivityEntity> getCustomActivityByCourseId(String courseId);
}
