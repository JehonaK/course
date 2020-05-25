package com.course.controller;

import com.course.entity.Evaluation;
import com.course.exception.ResponseException;
import com.course.service.EvaluationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("evaluation")
public class EvaluationController {

    private EvaluationServiceImpl evaluationService;

    public EvaluationController(EvaluationServiceImpl evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("{evaluationId}")
    public Evaluation getById(@PathVariable String evaluationId) {
        return evaluationService.findById(evaluationId);
    }

    @GetMapping
    public List<Evaluation> getEvaluationsByStudentIdAndCourseId(@RequestParam("studentId") String studentId, @RequestParam("courseId") String courseId) {
        return evaluationService.getEvaluationsByStudentIdAndCourseId(studentId, courseId);
    }

    @PostMapping
    public Evaluation create(@RequestBody Evaluation evaluation){
        try {
            return evaluationService.save(evaluation);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody Evaluation evaluation, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            evaluationService.update(evaluation, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) throws ResponseException {
        try {
            evaluationService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
