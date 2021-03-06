package com.course.integration.consumers;

import com.course.integration.models.SerializableTeacherSubjectConnection;
import com.course.service.CourseServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TeacherSubjectConnectionConsumer {

    private CourseServiceImpl courseService;

    public TeacherSubjectConnectionConsumer(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @RabbitListener(queues = {"${queue.connection.teacher.subject}"})
    public void handleNewUserReception(SerializableTeacherSubjectConnection teacherSubjectConnection) {
        courseService.handleNewTeacherSubjectConnection(teacherSubjectConnection);
    }

}
