package com.course.controller;

import com.course.entity.Course;
import com.course.entity.ForumPost;
import com.course.entity.Lesson;
import com.course.exception.ResponseException;
import com.course.service.ForumPostServiceImpl;
import com.course.service.LessonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("lesson")
public class LessonController {
    private LessonServiceImpl lessonService;

    public LessonController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public Lesson create(@RequestBody Lesson lesson){
        try {
            return lessonService.save(lesson);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody Lesson lesson, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            lessonService.update(lesson, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
    }

    @GetMapping("{lessonId}")
    public Lesson getById(@PathVariable String lessonId) {
        return lessonService.findById(lessonId);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) throws ResponseException {
        try {
            lessonService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("course")
    public List<Lesson> getLessonsByCourseId(@RequestParam String courseId) {
        return lessonService.getLessonsByCourseId(courseId);
    }
}
