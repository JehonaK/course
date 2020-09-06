package com.course.controller;

import com.course.entity.EvaluationEntity;
import com.course.exception.ResponseException;
import com.course.service.EvaluationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("evaluation")
public class EvaluationController extends BaseController {

    private EvaluationServiceImpl evaluationService;

    public EvaluationController(EvaluationServiceImpl evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("{evaluationId}")
    public EvaluationEntity getById(@PathVariable String evaluationId) {
        return evaluationService.findById(evaluationId);
    }

    @GetMapping
    public List<EvaluationEntity> getEvaluationsByStudentIdAndCourseId(@RequestParam(required = false) String studentId, @RequestParam("courseId") String courseId) {
        return evaluationService.getEvaluationsByStudentIdAndCourseId(studentId, courseId);
    }

    @PostMapping
    public EvaluationEntity create(@RequestBody EvaluationEntity evaluationEntity){
        try {
            return evaluationService.save(evaluationEntity);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody EvaluationEntity evaluationEntity, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            evaluationService.update(evaluationEntity, id);
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
            evaluationService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
