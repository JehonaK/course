package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.EvaluationEntity;
import com.course.entity.UserEntity;
import com.course.repository.BaseRepository;
import com.course.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl extends BaseServiceImpl<EvaluationEntity, String> implements EvaluationService {

    private EvaluationRepository evaluationRepository;
    private ActivityServiceImpl activityService;
    private UserServiceImpl userService;

    public EvaluationServiceImpl(BaseRepository<EvaluationEntity, String> baseRepository, EvaluationRepository evaluationRepository, ActivityServiceImpl activityService,
                                 UserServiceImpl userService) {
        super(baseRepository);
        this.evaluationRepository = evaluationRepository;
        this.activityService = activityService;
        this.userService = userService;
    }

    @Override
    public List<EvaluationEntity> getEvaluationsByStudentIdAndCourseId(String studentId, final String courseId) {
        studentId = studentId == null ? PerRequestIdStorage.getUserId() : studentId;
        UserEntity student = userService.findById(studentId);
        if (student == null) {
            throw new RuntimeException("UserEntity not found!");
        }
        List<EvaluationEntity> evaluationEntities = student.getEvaluationEntities();
        return evaluationEntities.stream().filter(e -> e.getActivityEntityId().getCourseEntityId().getId().equals(courseId)).collect(Collectors.toList());
//        List<ActivityEntity> activities = activityService.getActivitiesByCourseId(courseId);
//        List<String> activityIdList = activities.stream().map(ActivityEntity::getId).collect(Collectors.toList());
//        return evaluationRepository.findEvaluationsByStudentIdAndActivitiesIdList(studentId, activityIdList);
    }
}
