package com.course.repository;

import com.course.entity.FileUpload;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends BaseRepository<FileUpload, String>{
}
