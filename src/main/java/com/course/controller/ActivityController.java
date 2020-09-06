package com.course.controller;

import com.course.entity.ActivityEntity;
import com.course.exception.ResponseException;
import com.course.service.ActivityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("activity")
public class ActivityController extends BaseController {

    private ActivityServiceImpl activityService;

    public ActivityController(ActivityServiceImpl activityService) {
        this.activityService = activityService;
    }

    @GetMapping("{activityId}")
    public ActivityEntity getById(@PathVariable String activityId) {
        return activityService.findById(activityId);
    }

    @GetMapping
    public List<ActivityEntity> getActivitiesByCourseId(@RequestParam("courseId") String courseId) {
        return activityService.getActivitiesByCourseId(courseId);
    }

    @PostMapping
    public ActivityEntity create(@RequestBody ActivityEntity activityEntity){
        try {
            return activityService.save(activityEntity);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody ActivityEntity activityEntity, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            activityService.update(activityEntity, id);
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
            activityService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
