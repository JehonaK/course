package com.course.service;

import com.course.entity.Activity;
import com.course.repository.ActivityRepository;
import com.course.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends BaseServiceImpl<Activity, String> implements ActivityService{
    private ActivityRepository activityRepository;

    public ActivityServiceImpl(BaseRepository<Activity, String> baseRepository, ActivityRepository activityRepository) {
        super(baseRepository);
        this.activityRepository = activityRepository;
    }
}
