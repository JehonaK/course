package com.course.service;

import com.course.entity.FileUploadEntity;
import com.dropbox.core.DbxException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {
    List<FileUploadEntity> getFileUploadsByActivityId(String activityId);

    List<FileUploadEntity> getFileUploadsByLessonId(String lessonId);

    ResponseEntity<Resource> downloadFileByFileUploadId(String fileUploadId) throws DbxException, IOException;

    ResponseEntity<Resource> downloadLessonFileByFileUploadId(String fileUploadId) throws DbxException, IOException;

    FileUploadEntity uploadAndSaveFile(MultipartFile multipartFile, String activityId) throws DbxException, IOException;

    FileUploadEntity uploadAndSaveFileForLesson(MultipartFile multipartFile, String lessonId) throws DbxException, IOException;

    ResponseEntity<Resource> downloadMainFileByActivityId(String activityId);

}
