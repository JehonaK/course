package com.course.repository;

import com.course.entity.FileUploadEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends BaseRepository<FileUploadEntity, String>{
}
