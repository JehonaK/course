package com.course.controller;

import com.course.entity.CustomActivityEntity;
import com.course.exception.ResponseException;
import com.course.service.CustomActivityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("custom-activity")
public class CustomActivityController extends BaseController {

    private CustomActivityServiceImpl customActivityService;

    public CustomActivityController(CustomActivityServiceImpl customActivityService) {
        this.customActivityService = customActivityService;
    }

    @PostMapping
    public CustomActivityEntity create(@RequestBody CustomActivityEntity customActivityEntity){
        try {
            return customActivityService.save(customActivityEntity);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody CustomActivityEntity customActivityEntity, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            customActivityService.update(customActivityEntity, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
    }

    @GetMapping("{customActivityId}")
    public CustomActivityEntity getById(@PathVariable String customActivityId) {
        return customActivityService.findById(customActivityId);
    }

    @GetMapping
    public List<CustomActivityEntity> getByCourseId(@RequestParam String courseId) {
        return customActivityService.getCustomActivityByCourseId(courseId);
    }

    @Transactional
    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) throws ResponseException {
        try {
            customActivityService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
