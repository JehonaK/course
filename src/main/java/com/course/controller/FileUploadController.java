package com.course.controller;

import com.course.entity.FileUpload;
import com.course.exception.ResponseException;
import com.course.service.FileUploadServiceImpl;
import com.course.service.UploadingService;
import com.dropbox.core.DbxException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("upload")
public class FileUploadController {

    private FileUploadServiceImpl fileUploadService;
    private UploadingService uploadingService;

    public FileUploadController(FileUploadServiceImpl fileUploadService, UploadingService uploadingService) {
        this.fileUploadService = fileUploadService;
        this.uploadingService = uploadingService;
    }

    @GetMapping("{fileUploadId}")
    public FileUpload getById(@PathVariable String fileUploadId) {
        return fileUploadService.findById(fileUploadId);
    }

    @GetMapping("/download/activity/{fileUploadId}")
    public ResponseEntity<Resource> downloadActivityFileByFileUploadId(@PathVariable String fileUploadId) throws DbxException, IOException {
        return fileUploadService.downloadFileByFileUploadId(fileUploadId);
    }

    @GetMapping("/download/activity/{activityId}/main")
    public ResponseEntity<Resource> downloadActivityTeacherFileByFileUploadId(@PathVariable String activityId) throws DbxException, IOException {
        return fileUploadService.downloadMainFileByActivityId(activityId);
    }

    @GetMapping("/download/lesson/{fileUploadId}")
    public ResponseEntity<Resource> downloadLessonFileByFileUploadId(@PathVariable String fileUploadId) throws DbxException, IOException {
        return fileUploadService.downloadLessonFileByFileUploadId(fileUploadId);
    }

    @GetMapping("activity")
    public List<FileUpload> getFileUploadsByActivityId(@RequestParam("activityId") String activityId) {
        return fileUploadService.getFileUploadsByActivityId(activityId);
    }

    @GetMapping("lesson")
    public List<FileUpload> getFileUploadsByLessonId(@RequestParam("activityId") String activityId) {
        return fileUploadService.getFileUploadsByLessonId(activityId);
    }

    @PostMapping("activity")
    public FileUpload createForActivity(@RequestParam("file") MultipartFile multipartFile, @RequestParam("activityId") String activityId) throws IOException, DbxException {
        return fileUploadService.uploadAndSaveFile(multipartFile, activityId);
    }

    @PostMapping("lesson")
    public FileUpload createForLesson(@RequestParam("file") MultipartFile multipartFile, @RequestParam("lessonId") String lessonId) {
        try {
            return fileUploadService.uploadAndSaveFileForLesson(multipartFile, lessonId);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) throws ResponseException {
        try {
            fileUploadService.remove(id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
