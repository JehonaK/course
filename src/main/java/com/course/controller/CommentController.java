package com.course.controller;

import com.course.entity.Activity;
import com.course.entity.Comment;
import com.course.exception.ResponseException;
import com.course.service.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class CommentController {

    private CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment){
        try {
            return commentService.save(comment);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody Comment comment, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            commentService.update(comment, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
    }

    @GetMapping("{commentId}")
    public Comment getById(@PathVariable String commentId) {
        return commentService.findById(commentId);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) throws ResponseException {
        try {
            commentService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
