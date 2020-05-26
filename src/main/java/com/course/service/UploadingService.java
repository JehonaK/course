package com.course.service;

import com.course.entity.FileUpload;
import com.course.util.FileDataContainer;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.Metadata;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadingService {

    private DbxClientV2 dbxClient;

    public UploadingService(DbxClientV2 dbxClient) {
        this.dbxClient = dbxClient;
    }

    public FileDataContainer downloadFile(FileUpload fileUpload) throws DbxException {
        String downloadPath = "/" + fileUpload.getEvaluationId().getActivityId() + "/" + fileUpload.getId() + "/";
        String fileName = listFilesByPath(downloadPath).get(0);
        DbxDownloader downloader  = dbxClient.files().download(downloadPath + "/" + fileName);
        return new FileDataContainer(downloader.getInputStream(), fileName);
    }

    public void uploadFile(FileUpload fileUpload, InputStream fin, String originalFilename) throws IOException, DbxException {
        String uploadPath = "/" + fileUpload.getEvaluationId().getActivityId()+ "/" + fileUpload.getId() + "/" + originalFilename;
        dbxClient.files().uploadBuilder(uploadPath).uploadAndFinish(fin);
    }

    public List<String> listFilesByPath(String path) throws DbxException {
        return dbxClient.files().listFolder(path).getEntries().stream().map(Metadata::getName).collect(Collectors.toList());
    }

}
