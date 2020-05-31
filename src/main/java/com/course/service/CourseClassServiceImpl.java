package com.course.service;

import com.course.entity.CourseClass;
import com.course.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseClassServiceImpl extends BaseServiceImpl<CourseClass, String> implements CourseClassService{
    public CourseClassServiceImpl(BaseRepository<CourseClass, String> baseRepository) {
        super(baseRepository);
    }
}
