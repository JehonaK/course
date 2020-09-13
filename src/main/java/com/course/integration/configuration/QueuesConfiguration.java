package com.course.integration.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueuesConfiguration {

    @Value("${queue.user.new.student}")
    private String newStudentsQueueName;

    @Value("${queue.user.new.parent}")
    private String newParentsQueueName;

    @Value("${queue.user.new.teacher}")
    private String newTeachersQueueName;

    @Value("${queue.connection.teacher.subject}")
    private String teacherSubjectConnectionQueue;

    @Value("${queue.notification}")
    private String newNotificationQueueName;


    @Bean(name = "newStudentsQueue")
    public Queue newStudentsQueue() {
        return new Queue(newStudentsQueueName);
    }

    @Bean(name = "newParentsQueue")
    public Queue newParentsQueue() {
        return new Queue(newParentsQueueName);
    }

    @Bean(name = "newTeachersQueue")
    public Queue newTeachersQueue() {
        return new Queue(newTeachersQueueName);
    }

    @Bean(name = "teacherSubjectConnectionQueue")
    public Queue teacherSubjectConnectionQueue() {
        return new Queue(teacherSubjectConnectionQueue);
    }

    @Bean(name = "newNotificationQueue")
    public Queue newNotificationQueue() {
        return new Queue(newNotificationQueueName);
    }

}
