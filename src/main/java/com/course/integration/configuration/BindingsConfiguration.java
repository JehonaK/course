package com.course.integration.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindingsConfiguration {

    @Value("${binding.notification}")
    private String notificationBindingKey;

    @Bean
    public Binding bindDirectExchangeToNewNotificationQueue(@Qualifier("newNotificationQueue") Queue newNotificationQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(newNotificationQueue).to(directExchange).with(notificationBindingKey);
    }

}
