package com.course.integration.consumers;

import com.course.entity.User;
import com.course.integration.models.SerializableUser;
import com.course.service.UserServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    private UserServiceImpl userService;

    public UserConsumer(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = {"${queue.user.new.student}", "${queue.user.new.teacher}", "${queue.user.new.parent}"})
    public void handleNewCourseUserReception(SerializableUser serializableUser) {
        User user = new User();
        user.setId(serializableUser.getId());
        user.setEmail(serializableUser.getEmail());
        user.setFirstName(serializableUser.getFirstName());
        user.setLastName(serializableUser.getLastName());
        user.setRole(serializableUser.getRole());
        userService.save(user);
    }

}
