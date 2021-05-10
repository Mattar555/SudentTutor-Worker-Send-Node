package com.hmses.demo.service.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SimpleMailMessage preconfiguredMessage;

    public void sendSimpleMessage(String to) {

        SimpleMailMessage message = new SimpleMailMessage(preconfiguredMessage);
        preconfiguredMessage.setTo(to);
        javaMailSender.send(message);

    }
}
