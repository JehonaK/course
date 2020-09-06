package com.course.controller;

import com.course.entity.CourseEntity;
import com.course.exception.ResponseException;
import com.course.integration.models.SerializableTeacherSubjectConnection;
import com.course.service.CourseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("course")
public class CourseController extends BaseController {

    private CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @GetMapping("{courseId}")
    public CourseEntity getById(@PathVariable String courseId) {
        return courseService.findById(courseId);
    }

    @GetMapping("teacher")
    public List<CourseEntity> getCoursesByTeacherId(@RequestParam(required = false) String teacherId) {
        return courseService.getCoursesByTeacherId(teacherId);
    }

    @GetMapping("student")
    public List<CourseEntity> getCoursesByStudentId(@RequestParam(required = false) String studentId) {
        return courseService.getCoursesByStudentId(studentId);
    }

    @GetMapping("course-class")
    public List<CourseEntity> getCoursesByCourseClassId(@RequestParam String courseClassId) {
        return courseService.getCoursesByCourseClassId(courseClassId);
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody CourseEntity courseEntity, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            courseService.update(courseEntity, id);
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

    @PostMapping
    public void testCreateCourse() {
        ArrayList<String> studentIds = new ArrayList<>();
        studentIds.add("6619afe0-f0ef-4aa5-a1e4-a597e8174bbf");
        SerializableTeacherSubjectConnection connection = new SerializableTeacherSubjectConnection(
                "Math - Level 1", "ed62d8d5-20a9-4112-8c4d-d8769f7384d2", "subid", studentIds
        );
        courseService.handleNewTeacherSubjectConnection(connection);
    }

}
