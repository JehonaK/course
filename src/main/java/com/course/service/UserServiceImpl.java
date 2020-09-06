package com.course.service;

import com.course.entity.UserEntity;
import com.course.repository.BaseRepository;
import com.course.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, String> implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(BaseRepository<UserEntity, String> baseRepository, UserRepository userRepository) {
        super(baseRepository);
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findBatchOfUsersByIdList(List<String> idList) {
        return userRepository.findBatchOfUsersByIdList(idList);
    }

}
