package com.course.controller;

import com.course.entity.ForumPostEntity;
import com.course.exception.ResponseException;
import com.course.service.ForumPostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("forum-post")
public class ForumPostController extends BaseController {

    private ForumPostServiceImpl forumPostService;

    public ForumPostController(ForumPostServiceImpl forumPostService) {
        this.forumPostService = forumPostService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ForumPostEntity create(@RequestBody ForumPostEntity forumPostEntity){
        try {
            return forumPostService.save(forumPostEntity);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping
//    public List<ForumPostEntity> getForumPostsByStudentId(@RequestParam(required = false) String studentId) {
//        return forumPostService.getForumPostsByStudentId(studentId);
//    }

    @GetMapping
    public List<ForumPostEntity> getForumPostByCourseId(@RequestParam("courseId") String courseId) {
        return forumPostService.getForumPostByCourseId(courseId);
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody ForumPostEntity forumPostEntity, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            forumPostService.update(forumPostEntity, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
    }

    @GetMapping("{forumPostId}")
    public ForumPostEntity getById(@PathVariable String forumPostId) {
        return forumPostService.findById(forumPostId);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) throws ResponseException {
        try {
            forumPostService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
