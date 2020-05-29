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

    @GetMapping("/download/{fileUploadId}")
    public ResponseEntity<Resource> downloadFileByFileUploadId(@PathVariable String fileUploadId) throws DbxException, IOException {
        return fileUploadService.downloadFileByFileUploadId(fileUploadId);
    }

    @GetMapping()
    public List<FileUpload> getFileUploadsByActivityId(@RequestParam("activityId") String activityId) {
        return fileUploadService.getFileUploadsByActivityId(activityId);
    }

    @PostMapping
    public FileUpload create(@RequestParam("file") MultipartFile multipartFile, @RequestParam("activityId") String activityId) {
        try {
            return fileUploadService.uploadAndSaveFile(multipartFile, activityId);
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
