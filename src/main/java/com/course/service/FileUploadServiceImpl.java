package com.course.service;

import com.course.entity.Activity;
import com.course.entity.FileUpload;
import com.course.entity.Lesson;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class FileUploadServiceImpl extends BaseServiceImpl<FileUpload, String> implements FileUploadService {

    private FileUploadRepository fileUploadRepository;
    private ActivityServiceImpl activityService;
    private UploadingService uploadingService;
    private LessonServiceImpl lessonService;

    public FileUploadServiceImpl(BaseRepository<FileUpload, String> baseRepository, FileUploadRepository fileUploadRepository, ActivityServiceImpl activityService,
                                 UploadingService uploadingService, LessonServiceImpl lessonService) {
        super(baseRepository);
        this.fileUploadRepository = fileUploadRepository;
        this.activityService = activityService;
        this.uploadingService = uploadingService;
        this.lessonService = lessonService;
    }

    @Override
    public List<FileUpload> getFileUploadsByActivityId(String activityId) {
        Activity activity = activityService.findById(activityId);
//        List<Evaluation> evaluations = activity.getEvaluations();
//        List<FileUpload> uploads = new ArrayList<>();
//        for(Evaluation evaluation : evaluations) {
//            uploads.add(evaluation.getFileUpload());
//        }
        return activity.getFileUploads();
    }

    @Override
    public List<FileUpload> getFileUploadsByLessonId(String lessonId) {
        Lesson lesson = lessonService.findById(lessonId);
        return lesson.getFileUploads();
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

    @Override
    public ResponseEntity<Resource> downloadLessonFileByFileUploadId(String fileUploadId) throws DbxException, IOException {
        FileUpload fileUpload = findById(fileUploadId);
        FileDataContainer fileDataContainer = uploadingService.downloadLessonFile(fileUpload);
        InputStreamResource resource = new InputStreamResource(fileDataContainer.getInputStream());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileDataContainer.getFileName());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @Override
    public FileUpload uploadAndSaveFile(MultipartFile multipartFile, String activityId) throws DbxException, IOException {
        Activity activity = activityService.findById(activityId);
        FileUpload savedFileUpload = fileUploadRepository.save(new FileUpload(activity, new Timestamp(System.currentTimeMillis())));
        uploadingService.uploadFile(savedFileUpload, multipartFile.getInputStream(), multipartFile.getOriginalFilename());
        return savedFileUpload;
    }

    @Override
    public FileUpload uploadAndSaveFileForLesson(MultipartFile multipartFile, String lessonId) throws DbxException, IOException {
        Lesson lesson = lessonService.findById(lessonId);
        FileUpload savedFileUpload = fileUploadRepository.save(new FileUpload(lesson, new Timestamp(System.currentTimeMillis()), multipartFile.getOriginalFilename()));
        uploadingService.uploadFileForLesson(savedFileUpload, multipartFile.getInputStream(), multipartFile.getOriginalFilename());
        return savedFileUpload;
    }

}
