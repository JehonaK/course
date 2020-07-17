package com.course.service;

import com.course.entity.Course;
import com.course.entity.Lesson;
import com.course.repository.BaseRepository;
import com.course.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LessonServiceImpl extends BaseServiceImpl<Lesson, String> implements LessonService {
    private LessonRepository lessonRepository;
    private CourseServiceImpl courseService;

    public LessonServiceImpl(BaseRepository<Lesson, String> baseRepository, LessonRepository lessonRepository, CourseServiceImpl courseService) {
        super(baseRepository);
        this.lessonRepository = lessonRepository;
        this.courseService = courseService;
    }

    public List<Lesson> getLessonsByCourseId(String courseId){
        Course course = courseService.findById(courseId);
        List<Lesson> lessons = course.getLessons();
        Collections.sort(lessons);
        return lessons;
    }
}
