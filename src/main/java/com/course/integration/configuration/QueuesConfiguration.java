package com.course.integration.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueuesConfiguration {

    @Value("${queue.connection.teacher.subject}")
    private String teacherSubjectConnectionQueue;

    @Bean(name = "teacherSubjectConnectionQueue")
    public Queue teacherSubjectConnectionQueue() {
        return new Queue(teacherSubjectConnectionQueue);
    }

}
