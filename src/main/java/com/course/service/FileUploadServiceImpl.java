package com.course.service;

import com.course.PerRequestIdStorage;
import com.course.entity.ActivityEntity;
import com.course.entity.FileUploadEntity;
import com.course.entity.LessonEntity;
import com.course.entity.UserEntity;
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

@Service
public class FileUploadServiceImpl extends BaseServiceImpl<FileUploadEntity, String> implements FileUploadService {

    private FileUploadRepository fileUploadRepository;
    private ActivityServiceImpl activityService;
    private UploadingService uploadingService;
    private LessonServiceImpl lessonService;
    private UserServiceImpl userService;

    public FileUploadServiceImpl(BaseRepository<FileUploadEntity, String> baseRepository, FileUploadRepository fileUploadRepository, ActivityServiceImpl activityService,
                                 UploadingService uploadingService, LessonServiceImpl lessonService, UserServiceImpl userService) {
        super(baseRepository);
        this.fileUploadRepository = fileUploadRepository;
        this.activityService = activityService;
        this.uploadingService = uploadingService;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @Override
    public List<FileUploadEntity> getFileUploadsByActivityId(String activityId) {
        ActivityEntity activityEntity = activityService.findById(activityId);
        return activityEntity.getFileUploadEntities();
    }

    @Override
    public List<FileUploadEntity> getFileUploadsByLessonId(String lessonId) {
        LessonEntity lessonEntity = lessonService.findById(lessonId);
        return lessonEntity.getFileUploadEntities();
    }

    @Override
    public ResponseEntity<Resource> downloadFileByFileUploadId(String fileUploadId) throws DbxException, IOException {
        FileUploadEntity fileUploadEntity = findById(fileUploadId);
        FileDataContainer fileDataContainer = uploadingService.downloadFile(fileUploadEntity);
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
        FileUploadEntity fileUploadEntity = findById(fileUploadId);
        FileDataContainer fileDataContainer = uploadingService.downloadLessonFile(fileUploadEntity);
        InputStreamResource resource = new InputStreamResource(fileDataContainer.getInputStream());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileDataContainer.getFileName());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @Override
    public FileUploadEntity uploadAndSaveFile(MultipartFile multipartFile, String activityId) throws DbxException, IOException {
        ActivityEntity activityEntity = activityService.findById(activityId);
        UserEntity uploadedBy = userService.findById(PerRequestIdStorage.getUserId());
        FileUploadEntity fileUploadEntity = new FileUploadEntity(activityEntity, new Timestamp(System.currentTimeMillis()));
        fileUploadEntity.setUploadedBy(uploadedBy);
        FileUploadEntity savedFileUploadEntity = fileUploadRepository.save(fileUploadEntity);
        uploadingService.uploadFile(savedFileUploadEntity, multipartFile.getInputStream(), multipartFile.getOriginalFilename(), uploadedBy.getRole());
        return savedFileUploadEntity;
    }

    @Override
    public FileUploadEntity uploadAndSaveFileForLesson(MultipartFile multipartFile, String lessonId) throws DbxException, IOException {
        LessonEntity lessonEntity = lessonService.findById(lessonId);
        FileUploadEntity savedFileUploadEntity = fileUploadRepository.save(new FileUploadEntity(lessonEntity, new Timestamp(System.currentTimeMillis()), multipartFile.getOriginalFilename()));
        uploadingService.uploadFileForLesson(savedFileUploadEntity, multipartFile.getInputStream(), multipartFile.getOriginalFilename());
        return savedFileUploadEntity;
    }

    @Override
    public ResponseEntity<Resource> downloadMainFileByActivityId(String activityId) {
        FileDataContainer fileDataContainer = null;
        try {
            fileDataContainer = uploadingService.downloadMainFileByActivityId(activityId);
        } catch (DbxException ex) {
            ex.printStackTrace();
        }
        InputStreamResource resource = new InputStreamResource(fileDataContainer.getInputStream());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileDataContainer.getFileName());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
