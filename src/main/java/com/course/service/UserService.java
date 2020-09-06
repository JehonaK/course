package com.course.service;

import com.course.entity.UserEntity;

import java.util.List;

public interface UserService extends BaseService {

    List<UserEntity> findBatchOfUsersByIdList(List<String> idList);

}
