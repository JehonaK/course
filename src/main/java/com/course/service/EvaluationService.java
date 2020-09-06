package com.course.service;

import com.course.entity.EvaluationEntity;

import java.util.List;

public interface EvaluationService {
    List<EvaluationEntity> getEvaluationsByStudentIdAndCourseId(String studentId, String courseId);
}
