package com.course.service;

import com.course.entity.Activity;
import com.course.entity.Evaluation;
import com.course.entity.FileUpload;
import com.course.repository.BaseRepository;
import com.course.repository.FileUploadRepository;
import com.course.util.FileDataContainer;
import com.dropbox.core.DbxException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadServiceImpl extends BaseServiceImpl<FileUpload, String> implements FileUploadService {

    private FileUploadRepository fileUploadRepository;
    private ActivityServiceImpl activityService;
    private UploadingService uploadingService;

    public FileUploadServiceImpl(BaseRepository<FileUpload, String> baseRepository, FileUploadRepository fileUploadRepository, ActivityServiceImpl activityService,
                                 UploadingService uploadingService) {
        super(baseRepository);
        this.fileUploadRepository = fileUploadRepository;
        this.activityService = activityService;
        this.uploadingService = uploadingService;
    }

    @Override
    public List<FileUpload> getFileUploadsByActivityId(String activityId) {
        Activity activity = activityService.findById(activityId);
        List<Evaluation> evaluations = activity.getEvaluations();
        List<FileUpload> uploads = new ArrayList<>();
        for(Evaluation evaluation : evaluations) {
            uploads.add(evaluation.getFileUpload());
        }
        return uploads;
    }

    @Override
    public ResponseEntity<Resource> downloadFileByFileUploadId(String fileUploadId) throws DbxException, IOException {
        FileUpload fileUpload = findById(fileUploadId);
        FileDataContainer fileDataContainer = uploadingService.downloadFile(fileUpload);
        InputStreamResource resource = new InputStreamResource(fileDataContainer.getInputStream());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileDataContainer.getFileName());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
