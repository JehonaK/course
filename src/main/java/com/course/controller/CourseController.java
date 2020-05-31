package com.course.controller;

import com.course.entity.Course;
import com.course.exception.ResponseException;
import com.course.service.CourseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("course")
public class CourseController {

    private CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

//    @PostMapping
//    public Course create(@RequestBody Course course){
//        try {
//            return courseService.save(course);
//        } catch (Exception e) {
//            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("{courseId}")
    public Course getById(@PathVariable String courseId) {
        return courseService.findById(courseId);
    }

    @GetMapping("teacher")
    public List<Course> getCoursesByTeacherId(@RequestParam(required = false) String teacherId) {
        return courseService.getCoursesByTeacherId(teacherId);
    }

    @GetMapping("student")
    public List<Course> getCoursesByStudentId(@RequestParam(required = false) String studentId) {
        return courseService.getCoursesByStudentId(studentId);
    }

    @GetMapping("course-class")
    public List<Course> getCoursesByCourseClassId(@RequestParam String courseClassId) {
        return courseService.getCoursesByCourseClassId(courseClassId);
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody Course course, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            courseService.update(course, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
    }

    @Transactional
    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) throws ResponseException {
        try {
            courseService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
