package com.course.controller;

import com.course.entity.LessonEntity;
import com.course.exception.ResponseException;
import com.course.service.LessonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("lesson")
public class LessonController extends BaseController {
    private LessonServiceImpl lessonService;

    public LessonController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public LessonEntity create(@RequestBody LessonEntity lessonEntity){
        try {
            return lessonService.save(lessonEntity);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody LessonEntity lessonEntity, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            lessonService.update(lessonEntity, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
    }

    @GetMapping("{lessonId}")
    public LessonEntity getById(@PathVariable String lessonId) {
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

    @GetMapping("course/{courseId}")
    public List<LessonEntity> getLessonsByCourseId(@PathVariable String courseId) {
        return lessonService.getLessonsByCourseId(courseId);
    }

}
