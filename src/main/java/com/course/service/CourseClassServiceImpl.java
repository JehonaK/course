package com.course.service;

import com.course.entity.CourseClassEntity;
import com.course.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseClassServiceImpl extends BaseServiceImpl<CourseClassEntity, String> implements CourseClassService {
    public CourseClassServiceImpl(BaseRepository<CourseClassEntity, String> baseRepository) {
        super(baseRepository);
    }
}
