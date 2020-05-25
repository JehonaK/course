package com.course.service;

import com.course.entity.FileUpload;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class UploadingService {

    private DbxClientV2 dbxClient;

    public UploadingService(DbxClientV2 dbxClient) {
        this.dbxClient = dbxClient;
    }

    public InputStream downloadFile(FileUpload fileUpload) throws DbxException {
        DbxDownloader downloader  = dbxClient.files().download("/" + fileUpload.getEvaluationId().getActivityId() + "/" + fileUpload.getId());
        return downloader.getInputStream();
    }

    public void uploadFile(FileUpload fileUpload, InputStream fin) throws IOException, DbxException {
        dbxClient.files().uploadBuilder("/" + fileUpload.getEvaluationId().getActivityId()+ "/" + fileUpload.getId()).uploadAndFinish(fin);
    }

}
