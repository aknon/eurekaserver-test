package com.example.demo.controller;

import com.example.demo.email.EmailSender;
import com.example.demo.model.LimitConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @GetMapping("/limits")
    public LimitConfiguration retriveLimitsFromConfiguration() {

        return new LimitConfiguration(1000, 1);
    }

    @GetMapping("/send/mail")
    public void sendMail() {
        System.out.println("In Email controller.");
        EmailSender email = new EmailSender();
        email.send();

    }
}
