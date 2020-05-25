package com.course.service;

import com.course.entity.CustomActivity;
import com.course.repository.BaseRepository;
import com.course.repository.CustomActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomActivityServiceImpl extends BaseServiceImpl<CustomActivity, String> implements CustomActivityService {

    private CustomActivityRepository customActivityRepository;

    public CustomActivityServiceImpl(BaseRepository<CustomActivity, String> baseRepository, CustomActivityRepository customActivityRepository) {
        super(baseRepository);
        this.customActivityRepository = customActivityRepository;
    }

}
