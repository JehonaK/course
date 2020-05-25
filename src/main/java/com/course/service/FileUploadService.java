package com.course.service;

import com.course.entity.FileUpload;
import com.dropbox.core.DbxException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {
    List<FileUpload> getFileUploadsByActivityId(String activityId);

    ResponseEntity<Resource> downloadFileByFileUploadId(String fileUploadId) throws DbxException, IOException;
}
