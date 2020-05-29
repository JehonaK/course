package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.Activity;
import com.course.entity.Evaluation;
import com.course.entity.User;
import com.course.repository.BaseRepository;
import com.course.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl extends BaseServiceImpl<Evaluation, String> implements EvaluationService {

    private EvaluationRepository evaluationRepository;
    private ActivityServiceImpl activityService;
    private UserServiceImpl userService;

    public EvaluationServiceImpl(BaseRepository<Evaluation, String> baseRepository, EvaluationRepository evaluationRepository, ActivityServiceImpl activityService,
                                 UserServiceImpl userService) {
        super(baseRepository);
        this.evaluationRepository = evaluationRepository;
        this.activityService = activityService;
        this.userService = userService;
    }

    @Override
    public List<Evaluation> getEvaluationsByStudentIdAndCourseId(String studentId, final String courseId) {
        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
        User student = userService.findById(studentId);
        List<Evaluation> evaluations = student.getEvaluations();
        return evaluations.stream().filter(e -> e.getActivityId().getCourseId().getId().equals(courseId)).collect(Collectors.toList());
//        List<Activity> activities = activityService.getActivitiesByCourseId(courseId);
//        List<String> activityIdList = activities.stream().map(Activity::getId).collect(Collectors.toList());
//        return evaluationRepository.findEvaluationsByStudentIdAndActivitiesIdList(studentId, activityIdList);
    }
}
