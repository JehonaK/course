package com.course.service;

import com.course.entity.CourseEntity;
import com.course.entity.CustomActivityEntity;
import com.course.repository.BaseRepository;
import com.course.repository.CustomActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomActivityServiceImpl extends BaseServiceImpl<CustomActivityEntity, String> implements CustomActivityService {

    private CustomActivityRepository customActivityRepository;
    private CourseServiceImpl courseService;

    public CustomActivityServiceImpl(BaseRepository<CustomActivityEntity, String> baseRepository, CustomActivityRepository customActivityRepository,
                                     CourseServiceImpl courseService) {
        super(baseRepository);
        this.customActivityRepository = customActivityRepository;
        this.courseService = courseService;
    }

    @Override
    public List<CustomActivityEntity> getCustomActivityByCourseId(String courseId) {
        CourseEntity courseEntity = courseService.findById(courseId);
        return customActivityRepository.findByCourseEntityId(courseEntity);
    }
}
