package com.course.service;

import com.course.entity.User;
import com.course.repository.BaseRepository;
import com.course.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(BaseRepository<User, String> baseRepository, UserRepository userRepository) {
        super(baseRepository);
        this.userRepository = userRepository;
    }

}
