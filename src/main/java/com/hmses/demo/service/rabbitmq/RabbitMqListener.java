package com.hmses.demo.service.rabbitmq;

import com.hmses.demo.service.email.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMqListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${send.rabbitmq.queue}")
    public void receiveMessage(String emailAddress) throws IOException {
        //TODO: Add a DLQ for graceful handling of repeated failed messages.
        System.out.println(emailAddress);
        emailService.sendSimpleMessage(emailAddress);
    }
}
