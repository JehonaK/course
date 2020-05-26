package com.course.service;

import com.course.entity.User;

import java.util.List;

public interface UserService extends BaseService {

    List<User> findBatchOfUsersByIdList(List<String> idList);

}
