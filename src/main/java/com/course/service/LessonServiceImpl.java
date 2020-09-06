package com.course.service;

import com.course.entity.CourseEntity;
import com.course.entity.LessonEntity;
import com.course.repository.BaseRepository;
import com.course.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LessonServiceImpl extends BaseServiceImpl<LessonEntity, String> implements LessonService {
    private LessonRepository lessonRepository;
    private CourseServiceImpl courseService;

    public LessonServiceImpl(BaseRepository<LessonEntity, String> baseRepository, LessonRepository lessonRepository, CourseServiceImpl courseService) {
        super(baseRepository);
        this.lessonRepository = lessonRepository;
        this.courseService = courseService;
    }

    public List<LessonEntity> getLessonsByCourseId(String courseId){
        CourseEntity courseEntity = courseService.findById(courseId);
        List<LessonEntity> lessonEntities = courseEntity.getLessonEntities();
        Collections.sort(lessonEntities);
        return lessonEntities;
    }
}
