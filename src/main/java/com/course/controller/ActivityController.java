package com.course.controller;

import com.course.entity.Activity;
import com.course.exception.ResponseException;
import com.course.service.ActivityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("activity")
public class ActivityController {

    private ActivityServiceImpl activityService;

    public ActivityController(ActivityServiceImpl activityService) {
        this.activityService = activityService;
    }

    @GetMapping("{activityId}")
    public Activity getById(@PathVariable String activityId) {
        return activityService.findById(activityId);
    }

    @GetMapping
    public List<Activity> getActivitiesByCourseId(@RequestParam("courseId") String courseId) {
        return activityService.getActivitiesByCourseId(courseId);
    }

    @PostMapping
    public Activity create(@RequestBody Activity activity){
        try {
            return activityService.save(activity);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody Activity activity, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            activityService.update(activity, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) throws ResponseException {
        try {
            activityService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
