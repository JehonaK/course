package com.course.controller;

import com.course.entity.Course;
import com.course.entity.CustomActivity;
import com.course.exception.ResponseException;
import com.course.service.CourseServiceImpl;
import com.course.service.CustomActivityService;
import com.course.service.CustomActivityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("custom-activity")
public class CustomActivityController {

    private CustomActivityServiceImpl customActivityService;

    public CustomActivityController(CustomActivityServiceImpl customActivityService) {
        this.customActivityService = customActivityService;
    }

    @PostMapping
    public CustomActivity create(@RequestBody CustomActivity customActivity){
        try {
            return customActivityService.save(customActivity);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody CustomActivity customActivity, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            customActivityService.update(customActivity, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
    }

    @GetMapping("{customActivityId}")
    public CustomActivity getById(@PathVariable String customActivityId) {
        return customActivityService.findById(customActivityId);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) throws ResponseException {
        try {
            customActivityService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
