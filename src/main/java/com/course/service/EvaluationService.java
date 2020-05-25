package com.course.service;

import com.course.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    List<Evaluation> getEvaluationsByStudentIdAndCourseId(String studentId, String courseId);
}
