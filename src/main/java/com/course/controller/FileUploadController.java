package com.course.controller;

import com.course.entity.FileUpload;
import com.course.exception.ResponseException;
import com.course.service.FileUploadServiceImpl;
import com.course.service.UploadingService;
import com.dropbox.core.DbxException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("upload")
public class FileUploadController {

    private FileUploadServiceImpl fileUploadService;
    private UploadingService uploadingService;

    public FileUploadController(FileUploadServiceImpl fileUploadService) {
        this.fileUploadService = fileUploadService;
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
    public FileUpload create(@RequestBody FileUpload fileUpload, @RequestParam("file") MultipartFile multipartFile) {
        try {
            FileUpload upload = fileUploadService.save(fileUpload);
            uploadingService.uploadFile(upload, multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            return upload;
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public Map<String, String> update(@RequestBody FileUpload fileUpload, @PathVariable String id) throws ResponseException {
        Map<String, String> responseMap = new HashMap<>();
        try {
            fileUploadService.update(fileUpload, id);
            responseMap.put("id", id);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseMap;
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
