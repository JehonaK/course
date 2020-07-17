package com.course.service;

import com.course.entity.Activity;
import com.course.entity.Course;
import com.course.repository.ActivityRepository;
import com.course.repository.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ActivityServiceImpl extends BaseServiceImpl<Activity, String> implements ActivityService {

    private ActivityRepository activityRepository;
    private CourseServiceImpl courseService;

    public ActivityServiceImpl(BaseRepository<Activity, String> baseRepository, ActivityRepository activityRepository, CourseServiceImpl courseService) {
        super(baseRepository);
        this.activityRepository = activityRepository;
        this.courseService = courseService;
    }

    @Override
    public List<Activity> getActivitiesByCourseId(String courseId) {
        Course course = courseService.findById(courseId);

        List<Activity> activities = course.getActivities();
        Collections.sort(activities);
        return activities;
    }

    @Override
    public Activity save(Activity activity) {
        Activity savedActivity = super.save(activity);
        return savedActivity;
    }

}
