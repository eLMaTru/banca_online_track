package com.hhtech.botrack.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import com.hhtech.botrack.config.CustomPropertyConfig;
import com.hhtech.botrack.model.Mail;
import com.hhtech.botrack.service.EmailSenderService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private EmailSenderService emailSenderService;
    private CustomPropertyConfig customPropertyConfig;

    public MailController(EmailSenderService emailSenderService, CustomPropertyConfig customPropertyConfig) {
        this.emailSenderService = emailSenderService;
        this.customPropertyConfig = customPropertyConfig;
    }

    @GetMapping(value = "/send")
    public String sendMail() throws MessagingException {
        Mail mail = getMail();
        emailSenderService.sendEmail(mail);
        return "Check your email";

    }

    private Mail getMail() {
        Mail mail = new Mail();
        mail.setFrom(customPropertyConfig.mailFrom);
        mail.setTo("<jorgeluisdelossantoslopez@gmail.com>");
        mail.setSubject("Simple mail with AWS SES and Spring Boot");
        Map<String, Object> model = new HashMap<>();
        model.put("templateVariable", "Simple mail with aws..");
        mail.setModel(model);
        return mail;
    }
}
