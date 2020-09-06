package com.course.service;

import com.course.entity.ActivityEntity;
import com.course.entity.CourseEntity;
import com.course.repository.ActivityRepository;
import com.course.repository.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ActivityServiceImpl extends BaseServiceImpl<ActivityEntity, String> implements ActivityService {

    private ActivityRepository activityRepository;
    private CourseServiceImpl courseService;

    public ActivityServiceImpl(BaseRepository<ActivityEntity, String> baseRepository, ActivityRepository activityRepository, CourseServiceImpl courseService) {
        super(baseRepository);
        this.activityRepository = activityRepository;
        this.courseService = courseService;
    }

    @Override
    public List<ActivityEntity> getActivitiesByCourseId(String courseId) {
        CourseEntity courseEntity = courseService.findById(courseId);

        List<ActivityEntity> activities = courseEntity.getActivities();
        Collections.sort(activities);
        return activities;
    }

    @Override
    public ActivityEntity save(ActivityEntity activityEntity) {
        ActivityEntity savedActivityEntity = super.save(activityEntity);
        return savedActivityEntity;
    }

}
