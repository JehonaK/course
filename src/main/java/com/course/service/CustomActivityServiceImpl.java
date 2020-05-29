package com.course.service;

import com.course.entity.Course;
import com.course.entity.CustomActivity;
import com.course.repository.BaseRepository;
import com.course.repository.CustomActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomActivityServiceImpl extends BaseServiceImpl<CustomActivity, String> implements CustomActivityService {

    private CustomActivityRepository customActivityRepository;
    private CourseServiceImpl courseService;

    public CustomActivityServiceImpl(BaseRepository<CustomActivity, String> baseRepository, CustomActivityRepository customActivityRepository,
                                     CourseServiceImpl courseService) {
        super(baseRepository);
        this.customActivityRepository = customActivityRepository;
        this.courseService = courseService;
    }

    @Override
    public List<CustomActivity> getCustomActivityByCourseId(String courseId) {
        Course course = courseService.findById(courseId);
        return customActivityRepository.findByCourseId(course);
    }
}
