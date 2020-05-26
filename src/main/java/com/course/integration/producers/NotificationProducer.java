package com.course.integration.producers;

import com.course.integration.models.SerializableNotification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {

    @Value("${routing.notification}")
    private String notificationRoutingKey;

    @Value("${exchange.direct}")
    private String directExchange;

    private final RabbitTemplate rabbitTemplate;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(SerializableNotification serializableNotification) {
        rabbitTemplate.convertAndSend(directExchange, notificationRoutingKey, serializableNotification);
    }

}
