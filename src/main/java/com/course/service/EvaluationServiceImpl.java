package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.Activity;
import com.course.entity.Evaluation;
import com.course.repository.BaseRepository;
import com.course.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl extends BaseServiceImpl<Evaluation, String> implements EvaluationService {

    private EvaluationRepository evaluationRepository;
    private ActivityServiceImpl activityService;

    public EvaluationServiceImpl(BaseRepository<Evaluation, String> baseRepository, EvaluationRepository evaluationRepository, ActivityServiceImpl activityService) {
        super(baseRepository);
        this.evaluationRepository = evaluationRepository;
        this.activityService = activityService;
    }

    @Override
    public List<Evaluation> getEvaluationsByStudentIdAndCourseId(String studentId, String courseId) {
        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
        List<Activity> activities = activityService.getActivitiesByCourseId(courseId);
        List<String> activityIdList = activities.stream().map(Activity::getId).collect(Collectors.toList());
        return evaluationRepository.findEvaluationsByStudentIdAndActivitiesIdList(studentId, activityIdList);
    }
}
