package com.course.service;

import com.course.entity.FileUpload;
import com.dropbox.core.DbxException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {
    List<FileUpload> getFileUploadsByActivityId(String activityId);

    List<FileUpload> getFileUploadsByLessonId(String lessonId);

    ResponseEntity<Resource> downloadFileByFileUploadId(String fileUploadId) throws DbxException, IOException;

    ResponseEntity<Resource> downloadLessonFileByFileUploadId(String fileUploadId) throws DbxException, IOException;

    FileUpload uploadAndSaveFile(MultipartFile multipartFile, String activityId) throws DbxException, IOException;

    FileUpload uploadAndSaveFileForLesson(MultipartFile multipartFile, String lessonId) throws DbxException, IOException;

    ResponseEntity<Resource> downloadMainFileByActivityId(String activityId);

}
